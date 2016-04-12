package ua.kiev.prog;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
