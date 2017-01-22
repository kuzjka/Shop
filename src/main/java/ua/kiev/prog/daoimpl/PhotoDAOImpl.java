package ua.kiev.prog.daoimpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.dao.PhotoDao;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Photo;

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
    public List<Photo> listPhotos(Device device) {
            Query query= entityManager.createQuery("select p from Photo p where p.device =:device", Photo.class);
        query.setParameter("device", device);
        List<Photo> photos = query.getResultList();
        return photos;
    }
}










