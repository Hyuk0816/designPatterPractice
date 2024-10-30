package study.dev.designpatterpractice.payment.factory.object;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import study.dev.designpatterpractice.payment.enmus.PaymentType;
import study.dev.designpatterpractice.payment.factory.db.PaymentGateWay;
import study.dev.designpatterpractice.payment.factory.db.SamsungGateWay;
import study.dev.designpatterpractice.payment.repository.SamsungPaymentRepository;

@RequiredArgsConstructor
@Component
public class SamsungFactory implements PaymentFactory{
    private final SamsungPaymentRepository samsungPaymentRepository;

    @Override
    public PaymentGateWay createPaymentGateWay() {
        return new SamsungGateWay(samsungPaymentRepository);
    }

    @Override
    public String getPaymentType() {
        return PaymentType.SAMSUNG.name();
    }
}
