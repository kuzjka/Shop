package ua.kiev.prog.DAO;

import ua.kiev.prog.Classes.Device;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);
    void delete(int id);
    List <Device>listByManufacturer(String manufacturer);
    List<Device> listByType(String typeName);
    List<Device> list(String type, String pattern);
    Device findOne(int id);
    int total();
    List<Device> procFilter(List<String> proc);
    List<Device> ramFilter(List<Integer> ram);
    List<Device>priceFilter(String type, int min, int max, String dir);
}
