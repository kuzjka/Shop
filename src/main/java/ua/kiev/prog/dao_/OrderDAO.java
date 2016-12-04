package ua.kiev.prog.dao_;


import ua.kiev.prog.model.Order;
import ua.kiev.prog.model.User;

import java.util.List;

public interface OrderDAO {
    void add(Order order);
    List<Order> list(User user);

}
