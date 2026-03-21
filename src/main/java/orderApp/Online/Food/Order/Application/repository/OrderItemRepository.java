package orderApp.Online.Food.Order.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.Online.Food.Order.Application.entity.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
