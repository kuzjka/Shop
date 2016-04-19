package ua.kiev.prog;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);
    void delete(Device device);
    void delete(int id);
    List<Device>list(Cart cart);
    List<Device> listByType(String typeName);
    List<Device> list(String pattern);
    Device findOne(int id);
    int total();
    List<Device> priceFilter(int min, int max);
}
