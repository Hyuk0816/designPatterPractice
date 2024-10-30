package study.dev.designpatterpractice.orders.mapper;


import org.springframework.stereotype.Component;
import study.dev.designpatterpractice.orders.model.dto.FetchOrderList;
import study.dev.designpatterpractice.orders.model.entity.Orders;

import java.util.List;

public class OrdersMapper {

    public static List<FetchOrderList> mapFromOrdersToFetchOrderList(List<Orders> orders) {
        return orders.stream()
                .map(order -> new FetchOrderList(
                        order.getOrderId(),
                        order.getAmount(),
                        order.getTotalPrice(),
                        order.getPayment()
                )).toList();
    }
}
