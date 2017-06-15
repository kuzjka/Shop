package ua.kiev.prog.security;


import ua.kiev.prog.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

}
