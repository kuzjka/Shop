package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Type;
import ua.kiev.prog.model.User;

import java.util.List;
@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {
        @Query(value = "select * from devices where name like %?1", nativeQuery = true)
        Device findByName(String name);

        List<Device> findByTypeOrderByNameAsc(Type type);
        List<Device> findByTypeOrderByNameDesc(Type type);
        List<Device> findByTypeNameOrderByPriceAsc(String type);
        List<Device> findByTypeNameOrderByPriceDesc(String type);
        List<Device> findByNameStartingWith(String pattern);

}
