package com.manga.identityservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentTransactionResponse {
     Long id;
     String paymentId;
     String payerId;
     String status;
     Double amount;
     String currency;
     LocalDateTime timestamp;

     UserResponse user;
     VipPackageResponse vipPackage;
}
