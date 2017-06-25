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
    public Long totalItems(User user) {
            long sum = 0;
            List<Cart> carts =   cartRepository.findByUser(user);
            for(Cart c : carts){
                sum+=c.getItems();
            }
        return  sum;
    }

    /**
     * Returns total price of devices in all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public Long totalPrice(User user) {
        long sum=0;
        List<Cart>carts=cartRepository.findByUser(user);
        for(Cart c:carts){
            sum+=c.totalPrice();
        }
        return sum;
    }

    /**
     * Returns all carts created by certain user from database.
     */
    @Transactional(readOnly = true)
    public List<Cart> listCarts(User user) {
        List<Cart>carts=cartRepository.findByUser(user);
        return carts;
    }

    /**
     * Returns all orders made by certain user from database.
     */
    @Transactional(readOnly = true)
    public List<Order> listOrders(User user) {
        return orderRepository.findByUser(user);
    }
}
