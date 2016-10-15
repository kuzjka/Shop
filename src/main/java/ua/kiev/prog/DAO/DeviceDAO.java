package ua.kiev.prog.DAO;

import ua.kiev.prog.Classes.Device;
import ua.kiev.prog.Classes.User;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);
    void delete(int id);

    List<Device> manufacturerFilter(String type, List<String> manufacturers);
    List<Device> typeFilter(String typeName);
    List<Device> list(String type, String pattern);
    Device findOne(int id);

    Device findDevice(String name);
    int items(User user);
    int total(User user);

    List<Device> ramFilter(String type, List<Integer> ram, List<String> proc);
    List<Device>priceFilter(String type, int min, int max, String dir);
}
