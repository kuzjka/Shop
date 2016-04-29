package ua.kiev.prog.DAO;

import ua.kiev.prog.Classes.Device;

import java.util.List;

public interface DeviceDAO {
    void add(Device device);

    void delete(int id);

    List <Device>listByManufacturer(String manufacturer);
    List<Device> listByType(String typeName);
    List<Device> list(String pattern);
    Device findOne(int id);
    int total();


    List<Device> filter(List<Integer> ram , List<String>proc);
}
