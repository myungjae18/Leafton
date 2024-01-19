package idusw.leafton.model.service;

import idusw.leafton.model.DTO.OrderDTO;
import idusw.leafton.model.DTO.OrderItemDTO;
import idusw.leafton.model.DTO.ProductDTO;
import idusw.leafton.model.entity.Order;
import idusw.leafton.model.entity.OrderItem;
import idusw.leafton.model.repository.OrderItemRepository;
import idusw.leafton.model.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO){

        Order order = Order.createOrder(orderDTO);
        orderRepository.save(order);

        return OrderDTO.toOrderDTO(order);

    }

    @Override
    public OrderItemDTO addOrderItem(OrderDTO orderDTO, ProductDTO productDTO, int count){

        OrderItem orderItem = OrderItem.createOrderItem(orderDTO, productDTO, count);

        orderItemRepository.save(orderItem);

        return OrderItemDTO.toOrderItemDTO(orderItem);

    }
}