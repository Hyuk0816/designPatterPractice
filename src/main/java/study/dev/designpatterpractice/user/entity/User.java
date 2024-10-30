package study.dev.designpatterpractice.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.dev.designpatterpractice.order.model.Orders;
import study.dev.designpatterpractice.user.role.ROLE;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ROLE role;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;




}
