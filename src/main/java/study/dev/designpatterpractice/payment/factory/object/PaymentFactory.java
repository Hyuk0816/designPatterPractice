package study.dev.designpatterpractice.payment.factory.object;

import org.springframework.stereotype.Component;
import study.dev.designpatterpractice.payment.enmus.PaymentType;
import study.dev.designpatterpractice.payment.factory.db.PaymentGateWay;
@Component
public interface PaymentFactory {

    PaymentGateWay createPaymentGateWay();
    String getPaymentType();
}
