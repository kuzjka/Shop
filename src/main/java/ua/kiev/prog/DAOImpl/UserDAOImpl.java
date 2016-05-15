package ua.kiev.prog.DAOImpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.DAO.UserDAO;
import ua.kiev.prog.Classes.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User  findOne(String username) {
      return entityManager.getReference(User.class, username);

    }

    @Override
    public List<User> list(String username) {
        Query query= entityManager.createQuery("select u from User u where u.username=:username", User.class);
        query.setParameter("username", username);
        return  query.getResultList();
    }


}
