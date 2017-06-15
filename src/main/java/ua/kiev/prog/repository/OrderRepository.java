package ua.kiev.prog.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Order;
import ua.kiev.prog.model.User;

import java.util.List;
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByUser(User user);


}
