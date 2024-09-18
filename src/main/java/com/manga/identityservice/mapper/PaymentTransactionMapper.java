package com.manga.identityservice.mapper;
import com.manga.identityservice.dto.response.PaymentTransactionResponse;
import com.manga.identityservice.entity.PaymentTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, VipPackageMapper.class})
public interface PaymentTransactionMapper {

    PaymentTransactionMapper INSTANCE = Mappers.getMapper(PaymentTransactionMapper.class);

    PaymentTransactionResponse paymentTransactionToPaymentTransactionResponse(PaymentTransaction transaction);
}
