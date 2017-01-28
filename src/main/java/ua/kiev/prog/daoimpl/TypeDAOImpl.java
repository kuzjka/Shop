package ua.kiev.prog.daoimpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.dao.TypeDAO;
import ua.kiev.prog.model.Type;

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
    public Type findOne1(int id) {
        Query query = entityManager.createQuery("select t from Type t where t.id=:id", Type.class);
        query.setParameter("id", id);
        return (Type) query.getSingleResult();

    }

    @Override
    public Type findOne2(String name) {
        Query query = entityManager.createQuery("select t from Type t where t.name=:name", Type.class);
        query.setParameter("name", name);
        return (Type) query.getSingleResult();
    }

    @Override
    public List<Type> list() {
        Query query = entityManager.createQuery("select t from Type t order by t.name", Type.class);
        return query.getResultList();
    }
}
