package ua.kiev.prog.DAO;


import ua.kiev.prog.Classes.Order;
import ua.kiev.prog.Classes.User;

import java.util.List;

public interface OrderDAO {
    void add(Order order);
    List<Order> list(User user);

}
