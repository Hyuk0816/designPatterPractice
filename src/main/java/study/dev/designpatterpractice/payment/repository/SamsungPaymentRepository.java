package study.dev.designpatterpractice.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.dev.designpatterpractice.payment.model.entity.Samsung;

public interface SamsungPaymentRepository extends JpaRepository<Samsung, Long>  {

}
