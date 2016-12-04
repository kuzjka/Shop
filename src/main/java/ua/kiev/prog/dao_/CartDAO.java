package ua.kiev.prog.dao_;


import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.User;

import java.util.List;

public interface CartDAO {
    void add(Cart cart);
    void delete(int id);
    List<Cart> list(User user);
    Cart findOne(int id);


}
