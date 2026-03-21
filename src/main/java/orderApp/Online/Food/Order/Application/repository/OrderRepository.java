package orderApp.Online.Food.Order.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.Online.Food.Order.Application.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
