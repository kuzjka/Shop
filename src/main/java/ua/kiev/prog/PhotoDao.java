package ua.kiev.prog;


import java.util.List;

public interface PhotoDao {
    void add(Photo photo);
    List<Photo> getPhoto (Device device);

}
