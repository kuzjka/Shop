package ua.kiev.prog.DAOImpl;


import org.springframework.stereotype.Repository;
import ua.kiev.prog.DAO.RoleDAO;
import ua.kiev.prog.Classes.Role;

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
