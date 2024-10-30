package study.dev.designpatterpractice.orders.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import study.dev.designpatterpractice.orders.constants.OrdersConstants;
import study.dev.designpatterpractice.orders.model.dto.FetchOrderList;
import study.dev.designpatterpractice.orders.model.dto.OrderRequest;
import study.dev.designpatterpractice.orders.model.vo.OrderResponse;
import study.dev.designpatterpractice.orders.service.OrderQueryService;
import study.dev.designpatterpractice.orders.service.OrdersCommandService;
import study.dev.designpatterpractice.user.constants.UserConstants;
import study.dev.designpatterpractice.user.entity.User;
import study.dev.designpatterpractice.user.repository.UserRepository;
import study.dev.designpatterpractice.user.util.UserInfo;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersCommandService ordersCommandService;
    private final OrderQueryService orderQueryService;


    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody List<OrderRequest> orderRequests, @UserInfo User user) {

        log.info(user.getEmail() + " createOrder");

        ordersCommandService.orderProduct(user, orderRequests);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new OrderResponse(OrdersConstants.STATUS_201, OrdersConstants.MESSAGE_201));
    }

    @GetMapping("/my_order")
    public ResponseEntity<List<FetchOrderList>> fetchOrders(@UserInfo User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderQueryService.getMyOrders(user));
    }
}
