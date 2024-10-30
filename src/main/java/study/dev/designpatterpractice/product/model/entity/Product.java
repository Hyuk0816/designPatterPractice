package study.dev.designpatterpractice.product.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.dev.designpatterpractice.orders.model.entity.Orders;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

}
