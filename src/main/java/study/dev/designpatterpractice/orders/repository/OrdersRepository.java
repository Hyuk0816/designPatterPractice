package study.dev.designpatterpractice.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.dev.designpatterpractice.orders.model.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {


}
