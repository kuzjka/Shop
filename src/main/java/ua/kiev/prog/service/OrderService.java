package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.CartDAO;
import ua.kiev.prog.dao.DeviceDAO;
import ua.kiev.prog.dao.OrderDAO;
import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.Order;
import ua.kiev.prog.model.User;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private OrderDAO orderDAO;

    /**
     * Adds cart to database.
     */
    @Transactional
    public void addCart(Cart cart) {
        cartDAO.add(cart);
    }

    /**
     * Adds order to database.
     */
    @Transactional
    public void addOrder(Order order) {
        orderDAO.add(order);
    }

    /**
     * Returns number of devices in all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public Long totalItems(User user) {
        return deviceDAO.totalItems(user);
    }

    /**
     * Returns total price of devices in all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public int totalPrice(User user) {
        return deviceDAO.totalPrice(user);
    }

    /**
     * Returns all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public List<Cart> listCarts(User user) {
        return cartDAO.list(user);
    }

    /**
     * Returns all orders made by certain user from database.
     */
    @Transactional(readOnly = true)
    public List<Order> listOrders(User user) {
        return orderDAO.list(user);
    }
}
