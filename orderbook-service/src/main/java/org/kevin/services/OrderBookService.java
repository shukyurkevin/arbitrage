package org.kevin.services;

import lombok.Getter;
import org.kevin.enums.OrderSide;
import org.kevin.models.Order;

import java.util.*;

public class OrderBookService {
    private final Map<String, Order> orders =  new HashMap<>();

    @Getter
    private final TreeMap<Double, List<Order>> buyOrders =  new TreeMap<>(Comparator.reverseOrder());
    @Getter
    private final TreeMap<Double, List<Order>> sellOrders =  new TreeMap<>();

    public void putOrder(Order order) {
        orders.put(order.getOrderId(), order);

        TreeMap<Double, List<Order>> map = order.getSide() == OrderSide.BUY ? buyOrders : sellOrders;
        map.computeIfAbsent(order.getPrice(), k -> new ArrayList<>()).add(order);
    }

    public void cancelOrder(String orderId) {
        Order order = orders.remove(orderId);
        if (order == null)return;

        TreeMap<Double , List<Order>> map = order.getSide() == OrderSide.BUY ? buyOrders : sellOrders;
        List<Order> orders = map.get(order.getPrice());
        if (orders != null){
            orders.remove(order);
            if (orders.isEmpty()){
                map.remove(order.getPrice());
            }
        }

    }
    public Double getBestBid() {
        return buyOrders.isEmpty() ?null : buyOrders.firstKey();
    }
    public Double getBestAsk() {
        return sellOrders.isEmpty() ?null : sellOrders.firstKey();
    }

}
