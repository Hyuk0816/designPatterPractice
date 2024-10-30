package study.dev.designpatterpractice.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long productId;
    private Integer amount;
    private String payment;

}
