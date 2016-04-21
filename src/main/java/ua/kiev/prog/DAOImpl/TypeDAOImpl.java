package ua.kiev.prog.DAOImpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.DAO.TypeDAO;
import ua.kiev.prog.Classes.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TypeDAOImpl implements TypeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Type type) {
        entityManager.persist(type);
        }

    @Override
    public void delete(Type type) {
        entityManager.remove(type);
    }

    @Override
    public Type findOne(int id) {
        return entityManager.getReference(Type.class, id);
    }

    @Override
    public List<Type> list() {
        Query query = entityManager.createQuery("SELECT t FROM Type t", Type.class);
        return (List<Type>) query.getResultList();
    }
}
