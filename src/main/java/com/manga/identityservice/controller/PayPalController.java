package com.manga.identityservice.controller;

import com.manga.identityservice.dto.response.PaymentTransactionResponse;
import com.manga.identityservice.entity.PaymentTransaction;
import com.manga.identityservice.entity.User;
import com.manga.identityservice.entity.VipPackage;
import com.manga.identityservice.mapper.PaymentTransactionMapper;
import com.manga.identityservice.repository.PaymentTransactionRepository;
import com.manga.identityservice.service.JwtService;
import com.manga.identityservice.service.PayPalService;
import com.manga.identityservice.service.UserService;
import com.manga.identityservice.service.VipService;
import com.manga.identityservice.util.JwtUtil;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    private static final Logger logger = LoggerFactory.getLogger(PayPalController.class);

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private VipService vipService;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Autowired
    private UserService userService;

    private Authentication authenticateUserWithToken(String token) {
        try {
            // Validate the token
            if (jwtService.validateToken(token)) {
                // Extract username from token
                String username = jwtService.getUsernameFromToken(token);

                // Load user details
                UserDetails userDetails = userService.loadUserByUsername(username);

                // Create authentication object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Set authentication in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Return the authentication object
                return authentication;
            }
        } catch (Exception e) {
            // Log the error if authentication fails
            logger.error("Failed to authenticate user with token", e);
        }
        return null;
    }


    @PostMapping("/pay")
    public ResponseEntity<String> pay(Authentication authentication, @RequestParam("vipPackageId") int vipPackageId) {
        try {
            String username = authentication.getName();

            // Get the JWT token (from the Authentication object or the request headers)
            String jwtToken = JwtUtil.getJwtTokenFromHeader(); // Implement this to extract the JWT from the header

            if (jwtToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No JWT token found");
            }

            // Fetch the VIP package details using vipPackageId
            VipPackage vipPackage = vipService.findVipPackageById(vipPackageId);
            if (vipPackage == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VIP Package not found");
            }

            // Include JWT token in PayPal callback URLs
            String returnUrl = "http://localhost:8080/api/payments/success?vipPackageId=" + vipPackageId + "&token=" + jwtToken;
            String cancelUrl = "http://localhost:8080/api/payments/cancel?token=" + jwtToken;

            Payment payment = payPalService.createPayment(
                    vipPackage.getPrice(), "USD", "paypal",
                    "sale", "VIP Account Upgrade", cancelUrl, returnUrl);

            // Return approval URL
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return ResponseEntity.ok(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during payment creation");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment creation failed");
    }
    @GetMapping("/success")
    public ResponseEntity<PaymentTransactionResponse> successPay(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId,
            @RequestParam("vipPackageId") int vipPackageId,
            @RequestParam("token") String token) {

        // Authenticate the user using the token (JWT handling)
        Authentication authentication = authenticateUserWithToken(token);
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            // Confirm the PayPal payment
            Payment payment = payPalService.executePayment(paymentId, payerId);

            // Check if the payment is approved
            if (payment.getState().equals("approved")) {
                PaymentTransactionResponse paymentTransactionResponse = new PaymentTransactionResponse();

                // Populate PaymentTransactionResponse with payment details
                paymentTransactionResponse.setPaymentId(paymentId);
                paymentTransactionResponse.setPayerId(payerId);
                paymentTransactionResponse.setStatus(payment.getState());
                paymentTransactionResponse.setAmount(Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal()));
                paymentTransactionResponse.setCurrency(payment.getTransactions().get(0).getAmount().getCurrency());
                paymentTransactionResponse.setTimestamp(LocalDateTime.now());

                // Return the populated response
                return ResponseEntity.ok(paymentTransactionResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPay(@RequestParam("token") String token) {
        // Authenticate the user using the token
        Authentication authentication = authenticateUserWithToken(token);
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        // Log the cancellation
        String username = authentication.getName();
        logger.info("Payment was canceled by user: {}", username);

        return ResponseEntity.status(HttpStatus.OK).body("Payment canceled successfully");
    }

}
