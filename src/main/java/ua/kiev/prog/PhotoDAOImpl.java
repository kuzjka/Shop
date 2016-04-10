package ua.kiev.prog;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PhotoDAOImpl implements PhotoDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(Photo photo) {
        entityManager.merge(photo);
    }

    @Override
    public void delete(int id) {
            Photo p=entityManager.getReference(Photo.class, id);
            entityManager.remove(p);
    }

    @Override
    public byte[] get() {
        Query query=entityManager.createQuery("select p from Photo p", Photo.class);
        List<Photo>l=query.getResultList();
        Photo ph=l.get(l.size()-1);
        return ph.getBody();


    }
}
