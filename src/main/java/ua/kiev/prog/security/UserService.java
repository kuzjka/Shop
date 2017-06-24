package ua.kiev.prog.security;


import ua.kiev.prog.model.Role;
import ua.kiev.prog.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void saveRole(Role role);

    User findByUsername(String username);
    Role findRole(String name);

}
