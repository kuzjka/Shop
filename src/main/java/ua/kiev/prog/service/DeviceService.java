package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.*;
import ua.kiev.prog.dao.*;

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
    public List<Device> listDevices(Type type) {
        return deviceDAO.typeFilter(type);
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
    public Device findDeviceByName(String name) {
        return deviceDAO.findDevice2(name);
    }

    @Transactional(readOnly = true)
    public Device findDeviceById(int id) {
        return deviceDAO.findDevice1(id);
    }

    @Transactional(readOnly = true)
    public List<Device> searchDevices(String type, String pattern) {
        return deviceDAO.patternFilter(type, pattern);
    }


    @Transactional(readOnly = true)
    public int totalItems(User user) {
        return deviceDAO.totalItems(user);
    }

    @Transactional(readOnly = true)
    public int totalPrice(User user) {
        return deviceDAO.totalPrice(user);
    }

    @Transactional(readOnly = true)
    public List<Order> listOrders(User user) {
        return orderDAO.list(user);
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
        return photoDao.listPhotos(device);
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
