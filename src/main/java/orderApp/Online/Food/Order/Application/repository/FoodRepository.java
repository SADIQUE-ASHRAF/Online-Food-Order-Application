package orderApp.Online.Food.Order.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.Online.Food.Order.Application.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{

}
