package study.dev.designpatterpractice.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FetchOrderList {

    private Long orderId;
    private String productName;
    private Integer amount;
    private Integer totalPrice;
    private String payment;

}
