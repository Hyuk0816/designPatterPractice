package study.dev.designpatterpractice.orders.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {

    private String status;
    private String message;
}
