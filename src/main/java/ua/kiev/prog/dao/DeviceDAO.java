package ua.kiev.prog.dao;

import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Type;
import ua.kiev.prog.model.User;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);

    void delete(int id);

    List<Device> list();

    List<Device> typeFilter(String type, String dir);

    List<Device> manufacturerFilter(String type, List<String> manufacturers);

    List<Device> patternFilter(String type, String pattern);

    Device findDevice1(int id);

    Device findDevice2(String name);


    Long totalItems(User user);

    int totalPrice(User user);

    List<Device> ramFilter(String type, List<Integer> ram, List<String> proc);

    List<Device> priceSorter (String type, String dir);
}
