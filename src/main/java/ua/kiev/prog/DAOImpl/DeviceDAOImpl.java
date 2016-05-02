package ua.kiev.prog.DAOImpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.Classes.Cart;
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
    public List<Device> listByManufacturer(String manufacturer) {
        Query query=entityManager.createQuery("select d from Device d where d.manufacturer = :manufacturer",
                Device.class);
        query.setParameter("manufacturer", manufacturer);
        return query.getResultList();
    }


    @Override
    public List<Device> listByType(String typeName) {
        Query query;

        if (typeName.equals("all")) {
            query = entityManager.createQuery("SELECT d FROM Device d", Device.class);

        } else {
            query = entityManager.createQuery("SELECT d FROM Device d  WHERE d.type.name = :typeName", Device.class);
        query.setParameter("typeName", typeName);}

        return (List<Device>) query.getResultList();
    }

    @Override
    public List<Device> list(String pattern) {
        Query query = entityManager.createQuery("SELECT d FROM Device d WHERE d.name LIKE :pattern", Device.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Device>) query.getResultList();
    }

    @Override
    public Device findOne(int id) {

        Query query = entityManager.createQuery("select d from Device d where d.id=:id", Device.class);
        query.setParameter("id", id);
        Device d = (Device) query.getSingleResult();

        return d;
    }

    @Override
    public int total() {

        Query query = entityManager.createQuery("select c from Cart c", Cart.class);

        List<Cart> l = query.getResultList();
        int sum = 0;
        for (Cart c : l) {
            sum = sum + c.totalPrice();
        }
        return sum;


    }
    @Override
    public List<Device> procFilter(List<String> proc) {

            Query query= entityManager.createQuery("select d from Device d where d.processor " +
                    "in (:proc)", Device.class);
            query.setParameter("proc", proc);
            return query.getResultList();

       }



    @Override
    public List<Device> ramFilter(List<Integer> ram) {

        Query query= entityManager.createQuery("select d from Device d where d.ram in (:ram)", Device.class);
            query.setParameter("ram", ram);
        return query.getResultList();

        }

    @Override
    public List<Device> priceFilter(int min, int max, String dir) {

        if(max==-1){
            max = Integer.MAX_VALUE;
        }

        if(dir.equals("desc")){

         Query query=entityManager.createQuery("select d from  Device d where d.price between" +
                 " (:min) and (:max ) order by d.price desc ", Device.class);
            query.setParameter("min", min);
            query.setParameter("max", max);
        return  query.getResultList();}



         else{
     Query     query=entityManager.createQuery("select d from  Device d where d.price between" +
                    " (:min) and (:max )order by d.price  ", Device.class);
             query.setParameter("min", min);
             query.setParameter("max", max);

            return  query.getResultList();
        }}}

























