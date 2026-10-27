package com.ra.productservice.dto.event;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent implements Serializable {
    private Long orderId;
    private Long medicineId;
    private Integer quantity;
    private Double totalAmount;
    private String customerEmail;
    private LocalDateTime createdAt;
}
