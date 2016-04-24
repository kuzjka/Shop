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
    public List<Device> listByType(String typeName) {
        Query query;

        if (typeName != null) {
            query = entityManager.createQuery("SELECT d FROM Device d WHERE d.type.name = :typeName", Device.class);
            query.setParameter("typeName", typeName);
        } else {
            query = entityManager.createQuery("SELECT d FROM Device d", Device.class);
        }

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

        Query query=entityManager.createQuery("select d from Device d where d.id=:id", Device.class);
        query.setParameter("id" , id);
        Device d= (Device) query.getSingleResult();

        return d;
    }

    @Override
    public int total() {

        Query query = entityManager.createQuery("select c from Cart c", Cart.class);

        List<Cart>l=query.getResultList();
        int sum=0;
        for (Cart c : l) {
            sum=sum+c.totalPrice();
        }
        return sum;


    }

    @Override
    public List<Device> priceFilter(int max) {
        if(max == -1){
            Query query= entityManager.createQuery("select d from Device d ", Device.class);
            return query.getResultList();
        }else{
        Query query= entityManager.createQuery("select d from Device d where  d.price<=:max", Device.class);

        query.setParameter("max", max);
         return query.getResultList();

    }}

    public List<Device> ramFilter(List<Integer> ram) {
       if(ram.equals(null)){

            Query query = entityManager.createQuery("select d from Device d", Device.class);
            return query.getResultList();
        } else {
            Query query = entityManager.createQuery("select d from Device d", Device.class);
            List<Device> a = query.getResultList();
            List<Device> b = new ArrayList<>();

            for (Device d : a) {
                if (ram.contains(d.getRam())) {
                    b.add(d);


                }
            }


            return b;
        }



        }
    }









