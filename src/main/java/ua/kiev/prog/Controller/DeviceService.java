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
    public void addUser(User user){
        userDAO.add(user);}
    @Transactional
    public void addRole(Role role){roleDAO.add(role);}
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
        return deviceDAO.listByType(typeName);
    }


    @Transactional(readOnly = true)
    public List<Cart> listCarts() {
        return cartDAO.list();
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
    public List<Device> searchDevices(String pattern) {
        return deviceDAO.list(pattern);
    }

    @Transactional(readOnly = true)
    public int totalPrice() {
        return deviceDAO.total();
    }

    @Transactional(readOnly = true)
    public List<Order> listOrders() {
        return orderDAO.list();
    }

    @Transactional
    public void addOrder(Order order) {
        orderDAO.add(order);
    }
    @Transactional
    public void addPhoto(Photo photo){photoDao.add(photo);}
    @Transactional
    public List<Photo> getPhoto(Device device){ return photoDao.getPhoto(device);}
    @Transactional(readOnly = true)
    public List<Device>sort(String dir){return deviceDAO.sort(dir);}

    @Transactional(readOnly = true)
    public List<Device>ramFilter(List<Integer>ram){
        return  deviceDAO.ramFilter(ram);}
        @Transactional(readOnly = true)
        public List<Device> procFilter (List<String>proc){
            return  deviceDAO.procFilter(proc);}

    @Transactional(readOnly = true)
    public List<Device> listByManufacturer (String manufacturer){
        return deviceDAO.listByManufacturer(manufacturer);
    }
}
