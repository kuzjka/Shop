package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Order;
import ua.kiev.prog.model.User;
import ua.kiev.prog.service.DeviceService;
import ua.kiev.prog.service.UserService;

import java.util.List;

/**
 * Controller for order management
 */
@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cart_add_page", method = RequestMethod.GET)
    public String cart(
            Model model) {
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        model.addAttribute("items", deviceService.totalItems(findUser()));
        return "cart_add_page";
    }

    @RequestMapping(value = "/tocart/{id}/{n}", method = RequestMethod.GET)
    public String toCart(@PathVariable int id, @PathVariable int n, Model model) {
        Device device = deviceService.findDeviceById(id);
        Cart cart = new Cart(findUser(), device, 1);
        List<Cart> l = deviceService.listCarts(findUser());
        int count = 1;
        for (Cart c : l) {
            if (c.getDevice().getId() == id) {
                count = c.getItems() + n;
                c.setItems(count);
                if (count >= 1) {
                    deviceService.addCart(c);
                }
            }
        }
        if (count == 1 && n == 1) {
            deviceService.addCart(cart);
        }
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";
    }


    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public String orderAdd(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            Model model) {
        List<Cart> carts = deviceService.listCarts(findUser());
        for (Cart c : carts) {
            Order order = new Order(findUser(), name, address, phone, c);
            deviceService.addOrder(order);
        }

        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("orders", deviceService.listOrders(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "result_page";


    }

    @RequestMapping("/result_page")
    public String result(Model model) {
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("orders", deviceService.listOrders(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "result_page";
    }

    @RequestMapping(value = "/order_add_page", method = RequestMethod.GET)
    public String order(Model model) {

        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));

        return "order_add_page";
    }

    @RequestMapping(value = "/cart/delete/{id}")
    public String deleteCart(@PathVariable int id, Model model) {
        deviceService.deleteCart(id);
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";


    }

    public User findUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUser(username);
        return user;
    }
}
