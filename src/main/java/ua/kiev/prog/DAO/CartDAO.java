package ua.kiev.prog.DAO;


import ua.kiev.prog.Classes.Cart;
import ua.kiev.prog.Classes.User;

import java.util.List;

public interface CartDAO {
    void add(Cart cart);
    void delete(int id);
    List<Cart> list(User user);
    Cart findOne(int id);


}
