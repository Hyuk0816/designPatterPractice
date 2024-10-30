package study.dev.designpatterpractice.payment.factory.db;

import study.dev.designpatterpractice.orders.model.entity.Orders;

public interface PaymentGateWay {

    void processPayment(Orders orders);
}
