package study.dev.designpatterpractice.payment.factory.object;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import study.dev.designpatterpractice.payment.enmus.PaymentType;
import study.dev.designpatterpractice.payment.factory.db.PaymentGateWay;
import study.dev.designpatterpractice.payment.factory.db.TossGateWay;
import study.dev.designpatterpractice.payment.repository.TossPaymentRepository;
@RequiredArgsConstructor
@Component
public class TossFactory implements PaymentFactory {
    private final TossPaymentRepository tossPaymentRepository;

    @Override
    public PaymentGateWay createPaymentGateWay() {
        return new TossGateWay(tossPaymentRepository);
    }

    @Override
    public String getPaymentType() {
        return PaymentType.TOSS.name();
    }
}
