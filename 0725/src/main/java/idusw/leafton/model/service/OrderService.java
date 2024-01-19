package idusw.leafton.model.service;

import idusw.leafton.model.DTO.*;

public interface OrderService {
    OrderDTO addOrder(OrderDTO orderDTO);

    OrderItemDTO addOrderItem(OrderDTO orderDTO, ProductDTO productDTO, int count);
}