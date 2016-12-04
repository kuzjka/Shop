package ua.kiev.prog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao_.RoleDAO;
import ua.kiev.prog.dao_.UserDAO;
import ua.kiev.prog.model.Role;
import ua.kiev.prog.model.User;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    /**
     * Adds new user to database.
     */
    @Transactional
    public void addUser(User user) {
        userDAO.add(user);
    }

    /**
     * Adds new user role to database.
     */
    @Transactional
    public void addRole(Role role) {
        roleDAO.add(role);
    }

    /**
     * Returns user with certain username from database.
     */
    @Transactional(readOnly = true)
    public User findUser(String username) {
        return userDAO.findOne(username);
    }

    /**
     * Returns all users with certain username from database.
     */
    @Transactional(readOnly = true)
    public List<User> listUsers(String username) {
        return userDAO.list(username);
    }
}
