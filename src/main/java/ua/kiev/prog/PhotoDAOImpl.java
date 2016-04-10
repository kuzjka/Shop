package ua.kiev.prog;

import javassist.bytecode.ByteArray;
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
    public byte[] getPhoto (int id) {
            Photo p=entityManager.find(Photo.class, id);
            return p.getBody();


    }


}
