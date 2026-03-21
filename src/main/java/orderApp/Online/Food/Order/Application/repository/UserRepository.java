package orderApp.Online.Food.Order.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import orderApp.Online.Food.Order.Application.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
