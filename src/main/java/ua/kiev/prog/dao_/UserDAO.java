package ua.kiev.prog.dao_;


import ua.kiev.prog.model.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    void delete(User user);
    User  findOne(String username);
    List<User>list(String username);
}
