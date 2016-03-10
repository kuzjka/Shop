package ua.kiev.prog;

import org.springframework.stereotype.Repository;

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
    public List<Cart> list() {
        Query query = entityManager.createQuery("SELECT c FROM Cart c", Cart.class);
        return (List<Cart>) query.getResultList();
    }

    @Override
    public Cart findOne(int id) {
        return entityManager.getReference(Cart.class, id);
    }
}
