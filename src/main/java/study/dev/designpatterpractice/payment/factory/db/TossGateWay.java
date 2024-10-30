package study.dev.designpatterpractice.payment.factory.db;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.dev.designpatterpractice.orders.model.entity.Orders;
import study.dev.designpatterpractice.payment.model.entity.Toss;
import study.dev.designpatterpractice.payment.repository.TossPaymentRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TossGateWay implements PaymentGateWay{

    private final TossPaymentRepository tossPaymentRepository;

    @Override
    public void processPayment(Orders orders) {

        Toss toss = Toss.builder()
                .orders(orders)
                .regDate(LocalDateTime.now())
                .build();

        tossPaymentRepository.save(toss);
    }
}
