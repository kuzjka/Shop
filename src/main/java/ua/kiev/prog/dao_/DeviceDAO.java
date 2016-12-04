package ua.kiev.prog.dao_;

import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Type;
import ua.kiev.prog.model.User;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);

    void delete(int id);

    List<Device> list();

    List<Device> typeFilter(Type type);

    List<Device> manufacturerFilter(String type, List<String> manufacturers);

    List<Device> patternFilter(String type, String pattern);

    Device findDevice1(int id);

    Device findDevice2(String name);


    int totalItems(User user);

    int totalPrice(User user);

    List<Device> ramFilter(String type, List<Integer> ram, List<String> proc);

    List<Device> priceFilter(String type, int min, int max, String dir);
}
