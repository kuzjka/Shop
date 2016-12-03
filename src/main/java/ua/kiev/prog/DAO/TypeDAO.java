package ua.kiev.prog.dao;

import ua.kiev.prog.model.Type;

import java.util.List;

public interface TypeDAO {
    void add(Type type);
    void delete(Type type);
    Type findOne(int id);
    List<Type> list();
}
