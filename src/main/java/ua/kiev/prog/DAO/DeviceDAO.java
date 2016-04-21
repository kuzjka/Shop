package ua.kiev.prog.DAO;

import ua.kiev.prog.Classes.Cart;
import ua.kiev.prog.Classes.Device;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);

    void delete(int id);

    List<Integer> list2();
    List<Device> listByType(String typeName);
    List<Device> list(String pattern);
    Device findOne(int id);
    int total();
    List<Device> priceFilter(int min, int max);

    List<Device> ramFilter(int ram);
}
