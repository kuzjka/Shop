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
    private PhotoDao photoDao;

    /**
     * Adds device to database.
     */
    @Transactional
    public void addDevice(Device device) {
        deviceDAO.add(device);
    }

    /**
     * Adds type to database.
     */
    @Transactional
    public void addType(Type type) {
        typeDAO.add(type);
    }


    /**
     * Adds photo to database.
     */
    @Transactional
    public void addPhoto(Photo photo) {
        photoDao.add(photo);
    }

    /**
     * Removes cart from database.
     */
    @Transactional
    public void deleteCart(int id) {
        cartDAO.delete(id);
    }

    /**
     * Removes device from database.
     */
    @Transactional
    public void deleteDevice(int id) {
        deviceDAO.delete(id);
    }

    /**
     * Returns all types of devices from database.
     */
    @Transactional(readOnly = true)
    public List<Type> listTypes() {
        return typeDAO.list();
    }

    /**
     * Returns all  devices from database.
     */
    @Transactional(readOnly = true)
    public List<Device> listDevices() {
        return deviceDAO.list();
    }

    /**
     * Returns all devices of certain type from database.
     */
    @Transactional(readOnly = true)
    public List<Device> listDevicesByType(Type type) {
        return deviceDAO.typeFilter(type);
    }


    /**
     * Returns type with  certain id from database.
     */
    @Transactional(readOnly = true)
    public Type findType(int id) {
        return typeDAO.findOne(id);
    }

    /**
     * Returns cart with  certain id from database.
     */
    @Transactional(readOnly = true)
    public Cart findCart(int id) {
        return cartDAO.findOne(id);
    }

    /**
     * Returns device with certain name from database.
     */
    @Transactional(readOnly = true)
    public Device findDeviceByName(String name) {
        return deviceDAO.findDevice2(name);
    }

    /**
     * Returns device with certain id from database.
     */
    @Transactional(readOnly = true)
    public Device findDeviceById(int id) {
        return deviceDAO.findDevice1(id);
    }

    /**
     * Returns devices  from database according to search pattern.
     */
    @Transactional(readOnly = true)
    public List<Device> searchDevices(String type, String pattern) {
        return deviceDAO.patternFilter(type, pattern);
    }


    /**
     * Returns all photos of certain device from database.
     */
    @Transactional
    public List<Photo> getPhotos(Device device) {
        return photoDao.listPhotos(device);
    }

    @Transactional(readOnly = true)
    public List<Device> priceFilter(String type, int min, int max, String dir) {
        return deviceDAO.priceFilter(type, min, max, dir);
    }

    /**
     * Returns all devices with certain amount of ram and certain type of processor.
     */
    @Transactional(readOnly = true)
    public List<Device> ramFilter(String type, List<Integer> ram, List<String> proc) {
        return deviceDAO.ramFilter(type, ram, proc);
    }

    /**
     * Returns all devices with certain manufacturer from database.
     */
    @Transactional(readOnly = true)
    public List<Device> manufacturerFilter(String type, List<String> manufacturers) {
        return deviceDAO.manufacturerFilter(type, manufacturers);
    }
}
