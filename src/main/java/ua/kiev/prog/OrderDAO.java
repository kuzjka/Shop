package ua.kiev.prog;


import java.util.List;

public interface OrderDAO {
    void add(Order order);
    List<Order> list();
}
