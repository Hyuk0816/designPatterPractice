package study.dev.designpatterpractice.payment.factory.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.dev.designpatterpractice.orders.model.entity.Orders;
import study.dev.designpatterpractice.payment.model.entity.Samsung;
import study.dev.designpatterpractice.payment.repository.SamsungPaymentRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SamsungGateWay implements PaymentGateWay{

    private final SamsungPaymentRepository samsungPaymentRepository;

    @Override
    public void processPayment(Orders orders) {
        Samsung samsung = Samsung.builder()
                .orders(orders)
                .regDate(LocalDateTime.now())
                .build();

        samsungPaymentRepository.save(samsung);
    }
}
