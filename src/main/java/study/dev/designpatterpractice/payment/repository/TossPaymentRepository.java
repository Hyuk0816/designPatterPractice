package study.dev.designpatterpractice.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.dev.designpatterpractice.payment.model.entity.Toss;

public interface TossPaymentRepository extends JpaRepository<Toss,Long> {


}
