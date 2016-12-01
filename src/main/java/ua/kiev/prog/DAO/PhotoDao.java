package ua.kiev.prog.DAO;


import ua.kiev.prog.Classes.Device;
import ua.kiev.prog.Classes.Photo;

import java.util.List;

public interface PhotoDao {
    void add(Photo photo);

    List<Photo> listPhotos(Device device);


}
