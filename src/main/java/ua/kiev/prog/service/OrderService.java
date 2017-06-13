package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.repository.CartRepository;
import ua.kiev.prog.repository.DeviceRepository;
import ua.kiev.prog.repository.OrderRepository;
import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.Order;
import ua.kiev.prog.model.User;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Adds cart to database.
     */
    @Transactional
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    /**
     * Adds order to database.
     */
    @Transactional
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    /**
     * Returns number of devices in all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public Long totalItems() {
        return cartRepository.count();
    }

    /**
     * Returns total price of devices in all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public Long totalPrice(User user) {
        int sum=0;
        List<Cart>carts=cartRepository.findByUserId(user.getId());
        for(Cart c:carts){
            sum+=c.getItems();
        }
        return Long.valueOf(sum);
    }

    /**
     * Returns all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public List<Cart> listCarts(User user) {
        return cartRepository.findByUserId(user.getId());
    }

    /**
     * Returns all orders made by certain user from database.
     */
    @Transactional(readOnly = true)
    public List<Order> listOrders(User user) {
        return orderRepository.findByUserId(user.getId());
    }
}
