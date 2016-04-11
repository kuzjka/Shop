package ua.kiev.prog;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LoginDAOImpl implements LoginDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Login login) {
        entityManager.merge(login);
    }

    @Override
    public void delete(Login login) {
        entityManager.remove(login);
    }
}
