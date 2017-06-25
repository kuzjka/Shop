package ua.kiev.prog.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.User;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    List<Cart> findByUser(User user);




}
