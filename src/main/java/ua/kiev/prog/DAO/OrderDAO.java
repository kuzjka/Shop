package ua.kiev.prog.DAO;


import ua.kiev.prog.Classes.Order;

import java.util.List;

public interface OrderDAO {
    void add(Order order);
    List<Order> list();
}
