package ua.kiev.prog.dao_;


import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Photo;

import java.util.List;

public interface PhotoDao {
    void add(Photo photo);

    List<Photo> listPhotos(Device device);


}
