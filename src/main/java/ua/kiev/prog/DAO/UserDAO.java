package ua.kiev.prog.DAO;


import ua.kiev.prog.Classes.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    void delete(User user);
    User findOne(String username);
}
