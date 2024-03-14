package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.adapter.out.persistence.entity.PaymentEntity;
import com.example.coffeeshop.application.models.Payment;
import com.example.coffeeshop.application.ports.out.Payments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentsJpaAdapter implements Payments {

    private final PaymentsJpaRepository paymentsJpaRepository;

    @Override
    public Payment findPaymentByOrderId(UUID orderId) {
        return paymentsJpaRepository.findByOrderId(orderId)
                .map(PaymentEntity::toDomain)
                .orElseThrow();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentsJpaRepository.save(PaymentEntity.fromDomain(payment)).toDomain();
    }
}
