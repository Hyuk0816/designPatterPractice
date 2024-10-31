package study.dev.designpatterpractice.orders.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.designpatterpractice.orders.model.dto.OrderRequest;
import study.dev.designpatterpractice.orders.model.entity.Orders;
import study.dev.designpatterpractice.orders.repository.OrdersRepository;
import study.dev.designpatterpractice.payment.factory.db.PaymentGateWay;
import study.dev.designpatterpractice.payment.factory.object.PaymentFactory;
import study.dev.designpatterpractice.product.model.entity.Product;
import study.dev.designpatterpractice.product.repository.ProductRepository;
import study.dev.designpatterpractice.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class OrdersCommandService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final Map<String, PaymentFactory> factoryMap;

    @Autowired
    public OrdersCommandService(OrdersRepository ordersRepository, ProductRepository productRepository,
                                ApplicationContext applicationContext) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
        this.factoryMap = new HashMap<>();

        Map<String, PaymentFactory> beans = applicationContext.getBeansOfType(PaymentFactory.class);
        log.info(beans.values() + " OrdersCommandService");

        for (PaymentFactory factory : beans.values()) {
            factoryMap.put(factory.getPaymentType(), factory);
        }
    }

    @Transactional
    public void orderProduct(User user, List<OrderRequest> orderRequestList) {


        orderRequestList.forEach(orderRequest -> {
            Product product = productRepository.findByProductId(orderRequest.getProductId());

            //총 가격 계산
            Integer totalPrice = orderRequest.getAmount() * product.getPrice();

            // Orders
            Orders orders = Orders.builder()
                    .product(product)
                    .amount(orderRequest.getAmount())
                    .payment(orderRequest.getPayment())
                    .user(user)
                    .totalPrice(totalPrice)
                    .build();

            // 주문 저장
            ordersRepository.save(orders);

            //페이먼트 실행
            PaymentFactory paymentFactory = factoryMap.get(orderRequest.getPayment());
            PaymentGateWay paymentGateWay = paymentFactory.createPaymentGateWay();
            log.info("=============================================================");
            log.info(String.valueOf(paymentFactory));
            paymentGateWay.processPayment(orders);
        });
    }

}
