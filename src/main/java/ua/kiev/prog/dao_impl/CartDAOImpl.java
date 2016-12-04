package ua.kiev.prog.dao_impl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.User;
import ua.kiev.prog.dao_.CartDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Cart cart) {


        entityManager.merge(cart);
    }



    @Override
    public void delete(int id){
        Cart c=entityManager.getReference(Cart.class, id);
        entityManager.remove(c);
    }
    @Override
    public List<Cart> list(User user) {
        Query query = entityManager.createQuery("SELECT c FROM Cart c where c.user=:user", Cart.class);
        query.setParameter("user", user);
        return (List<Cart>) query.getResultList();
    }

    @Override
    public Cart findOne(int id) {

        Query query = entityManager.createQuery("select c from Cart c where c.id=:id", Cart.class);
        query.setParameter("id" , id);

        return (Cart) query.getSingleResult();
    }
}
