package ua.kiev.prog;

import javassist.bytecode.ByteArray;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    public List<Photo> getPhoto (Device device) {
            Query query= entityManager.createQuery("select p from Photo p where p.device =:device", Photo.class);
        List<Photo>l=query.getResultList();
        return l;}}










