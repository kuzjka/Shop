package ua.kiev.prog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.Classes.*;
import ua.kiev.prog.DAO.*;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private TypeDAO typeDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private PhotoDao photoDao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    public void addDevice(Device device) {
        deviceDAO.add(device);
    }

    @Transactional
    public void addType(Type type) {
        typeDAO.add(type);
    }

    @Transactional
    public void addCart(Cart cart) {
        cartDAO.add(cart);
    }

    @Transactional
    public void addUser(User user) {
        userDAO.add(user);
    }

    @Transactional
    public void addRole(Role role) {
        roleDAO.add(role);
    }

    @Transactional
    public void deleteCart(int id) {
        cartDAO.delete(id);
    }

    @Transactional
    public void deleteDevice(int id) {
        deviceDAO.delete(id);
    }


    @Transactional(readOnly = true)
    public List<Type> listTypes() {
        return typeDAO.list();
    }

    @Transactional(readOnly = true)
    public List<Device> listDevices(String typeName) {
        return deviceDAO.typeFilter(typeName);
    }


    @Transactional(readOnly = true)
    public List<Cart> listCarts(User user) {
        return cartDAO.list(user);
    }

    @Transactional(readOnly = true)
    public Type findType(int id) {
        return typeDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    public Cart findCart(int id) {
        return cartDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    public Device findDevice(int id) {
        return deviceDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    public Device findDevice2(String name) {
        return deviceDAO.findDevice(name);
    }
    @Transactional(readOnly = true)
    public List<Device> searchDevices(String type, String pattern) {
        return deviceDAO.list(type, pattern);
    }

    @Transactional(readOnly = true)
    public int items(User user) {
        return deviceDAO.items(user);
    }

    @Transactional(readOnly = true)
    public int totalPrice(User user) {
        return deviceDAO.total(user);
    }

    @Transactional(readOnly = true)
    public List<Order> listOrders(User user) {
        return orderDAO.list(user);
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        return userDAO.findOne(username);
    }

    @Transactional(readOnly = true)
    public List<User> listUsers(String username) {
        return userDAO.list(username);
    }

    @Transactional
    public void addOrder(Order order) {
        orderDAO.add(order);
    }

    @Transactional
    public void addPhoto(Photo photo) {
        photoDao.add(photo);
    }

    @Transactional
    public List<Photo> getPhotos(Device device) {
        return photoDao.getPhotos(device);
    }

    @Transactional(readOnly = true)
    public List<Device> priceFilter(String type, int min, int max, String dir) {
        return deviceDAO.priceFilter(type, min, max, dir);
    }

    @Transactional(readOnly = true)
    public List<Device> ramFilter(String type, List<Integer> ram, List<String> proc) {
        return deviceDAO.ramFilter(type, ram, proc);
    }

    @Transactional(readOnly = true)
    public List<Device> manufacturerFilter(String type, List<String> manufacturers) {
        return deviceDAO.manufacturerFilter(type, manufacturers);
    }
}
