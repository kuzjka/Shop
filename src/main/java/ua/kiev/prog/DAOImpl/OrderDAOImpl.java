package ua.kiev.prog.DAOImpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.Classes.User;
import ua.kiev.prog.DAO.OrderDAO;
import ua.kiev.prog.Classes.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Order order) {
        entityManager.merge(order);
    }

    @Override
    public List<Order> list(User user) {
        Query query = entityManager.createQuery("select  o from Order o where o.user=:user", Order.class);
        query.setParameter("user", user);
        return (List<Order>) query.getResultList();
    }

}
