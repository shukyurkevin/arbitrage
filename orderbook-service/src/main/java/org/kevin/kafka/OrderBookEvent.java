package org.kevin.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kevin.enums.OrderSide;

@Data
@AllArgsConstructor
public class OrderBookEvent {
    private String exchange;
    private String orderId;
    private double price;
    private double volume;
    private OrderSide side;
    private String type;
}
