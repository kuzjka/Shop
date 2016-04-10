package ua.kiev.prog;


import java.util.List;

public interface PhotoDao {
    void add(Photo photo);
    void delete(int id);
    byte[] get();
}
