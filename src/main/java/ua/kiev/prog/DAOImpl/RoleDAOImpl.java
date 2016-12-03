package ua.kiev.prog.daoimpl;


import org.springframework.stereotype.Repository;
import ua.kiev.prog.dao.RoleDAO;
import ua.kiev.prog.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(Role role) {
        entityManager.merge(role);
    }
}
