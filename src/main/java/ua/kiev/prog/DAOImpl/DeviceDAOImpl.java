package ua.kiev.prog.DAOImpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.Classes.Cart;
import ua.kiev.prog.Classes.User;
import ua.kiev.prog.DAO.DeviceDAO;
import ua.kiev.prog.Classes.Device;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DeviceDAOImpl implements DeviceDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Device device) {


        entityManager.merge(device);
    }


    @Override
    public void delete(int id) {


        Device d = entityManager.getReference(Device.class, id);
        entityManager.remove(d);
    }

    @Override
    public List<Device> manufacturerFilter(String type, List<String> manufacturers) {
        Query query;
        if (manufacturers.size() > 0) {
            query = entityManager.createQuery("select d from Device d where d.type.name=:type " +
                    "and d.manufacturer in(:manufacturers)", Device.class);
        query.setParameter("type", type);
            query.setParameter("manufacturers", manufacturers);
        } else {
            query = entityManager.createQuery("select d from Device d where d.type.name=:type", Device.class);
            query.setParameter("type", type);
        }
        return query.getResultList();
    }


    @Override
    public List<Device> typeFilter(String typeName) {
        Query query;

        if (typeName.equals("all")) {
            query = entityManager.createQuery("SELECT d FROM Device d", Device.class);

        } else {
            query = entityManager.createQuery("SELECT d FROM Device d  WHERE d.type.name = :typeName", Device.class);
            query.setParameter("typeName", typeName);
        }

        return (List<Device>) query.getResultList();
    }

    @Override
    public List<Device> list(String type, String pattern) {
        if (type.equals("all")) {
            Query query = entityManager.createQuery("SELECT d FROM Device d " +
                    " where d.name LIKE :pattern", Device.class);
            query.setParameter("pattern", "%" + pattern + "%");
            return (List<Device>) query.getResultList();
        } else {
            Query query = entityManager.createQuery("SELECT d FROM Device d " +
                    " where d.name LIKE :pattern and d.type.name=:type", Device.class);
            query.setParameter("pattern", "%" + pattern + "%");
            query.setParameter("type", type);
            return (List<Device>) query.getResultList();
        }
    }

    @Override
    public Device findOne(int id) {

        Query query = entityManager.createQuery("select d from Device d where d.id=:id", Device.class);
        query.setParameter("id", id);
        Device d = (Device) query.getSingleResult();

        return d;
    }

    @Override
    public Device findDevice(String name) {
        Query query = entityManager.createQuery("select d from Device d where d.name=:name", Device.class);
        query.setParameter("name", name);
        return (Device) query.getSingleResult();
    }
    @Override
    public int items(User user) {
        Query query = entityManager.createQuery("select c from Cart c where c.user=:user", Cart.class);
        query.setParameter("user", user);
        List<Cart> l = query.getResultList();
        int sum = 0;
        for (Cart c : l) {
            sum = sum + c.getItems();
        }
        return sum;
    }

    @Override
    public int total(User user) {

        Query query = entityManager.createQuery("select c from Cart c where c.user=:user", Cart.class);
        query.setParameter("user", user);
        List<Cart> l = query.getResultList();
        int sum = 0;
        for (Cart c : l) {
            sum = sum + c.totalPrice();
        }
        return sum;


    }

    @Override
    public List<Device> ramFilter(String type, List<Integer> ram, List<String> proc) {
        Query query = null;

        if (ram.size() > 0 && proc.size() > 0) {

            query = entityManager.createQuery("select d from Device d where d.type.name=:type " +
                    " and d.ram in (:ram) and d.processor in(:proc)", Device.class);
            query.setParameter("ram", ram);
            query.setParameter("proc", proc);
            query.setParameter("type", type);


        }
        if (ram.size() > 0 && proc.size() == 0) {

            query = entityManager.createQuery("select d from Device d where d.type.name=:type" +
                            " and d.ram in (:ram) "
                    , Device.class);

            query.setParameter("ram", ram);
            query.setParameter("type", type);


        }
        if (ram.size() == 0 && proc.size() > 0) {
            query = entityManager.createQuery("select d from  Device d where d.type.name=:type" +
                    " and d.processor in (:proc)", Device.class);
            query.setParameter("proc", proc);
            query.setParameter("type", type);
        }
        if (ram.size() == 0 && proc.size() == 0) {
            query = entityManager.createQuery("select d from  Device d where d.type.name=:type", Device.class);
            query.setParameter("type", type);
        }
        return query.getResultList();
    }


    @Override
    public List<Device> priceFilter(String type, int min, int max, String dir) {
        Query query;
        if (max == -1) {
            max = Integer.MAX_VALUE;
        }

        if (dir.equals("desc")) {

            query = entityManager.createQuery("select d from  Device d where" +
                    " d.type.name=:type and d.price between" +
                    " (:min) and (:max ) order by d.price desc ", Device.class);
            query.setParameter("type", type);
            query.setParameter("min", min);
            query.setParameter("max", max);
        } else {
            query = entityManager.createQuery("select d from  Device d where d.type.name=:type " +
                    "and  d.price between (:min) and (:max )order by d.price  ", Device.class);
            query.setParameter("type", type);
            query.setParameter("min", min);
            query.setParameter("max", max);
        }

        return query.getResultList();
    }
}

























