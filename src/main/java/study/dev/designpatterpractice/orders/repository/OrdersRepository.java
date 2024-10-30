package study.dev.designpatterpractice.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.dev.designpatterpractice.orders.model.entity.Orders;

import java.util.List;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o join fetch o.user u where u.userId = :userId")
    List<Orders> findByUserId(Long userId);

}
