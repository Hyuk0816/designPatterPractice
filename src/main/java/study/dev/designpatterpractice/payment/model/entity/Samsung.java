package study.dev.designpatterpractice.payment.model.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.dev.designpatterpractice.orders.model.entity.Orders;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "samsung")
public class Samsung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", referencedColumnName = "orders_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Orders orders;

    @Column(name = "reg_date")
    private LocalDateTime regDate;


}
