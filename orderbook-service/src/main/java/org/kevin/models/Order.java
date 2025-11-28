package org.kevin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kevin.enums.OrderSide;

@Data
@AllArgsConstructor
public class Order {
    private String orderId;
    private double price;
    private double volume;
    private OrderSide side;

}
