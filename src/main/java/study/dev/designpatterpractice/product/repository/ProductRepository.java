package study.dev.designpatterpractice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.dev.designpatterpractice.product.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(Long Id);

}
