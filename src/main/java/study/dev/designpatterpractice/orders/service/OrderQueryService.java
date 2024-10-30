package study.dev.designpatterpractice.orders.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.designpatterpractice.orders.mapper.OrdersMapper;
import study.dev.designpatterpractice.orders.model.dto.FetchOrderList;
import study.dev.designpatterpractice.orders.model.entity.Orders;
import study.dev.designpatterpractice.orders.repository.OrdersRepository;
import study.dev.designpatterpractice.user.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrdersRepository ordersRepository;


    @Transactional(readOnly = true)
    public List<FetchOrderList> getMyOrders(User user) {

        List<Orders> orderList = ordersRepository.findByUserId(user.getUserId());

        return OrdersMapper.mapFromOrdersToFetchOrderList(orderList);
    }

}
