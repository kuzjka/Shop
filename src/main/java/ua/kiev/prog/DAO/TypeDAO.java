package ua.kiev.prog.DAO;

import ua.kiev.prog.Classes.Type;

import java.util.List;

public interface TypeDAO {
    void add(Type type);
    void delete(Type type);
    Type findOne(int id);
    List<Type> list();
}