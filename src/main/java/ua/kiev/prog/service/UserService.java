package ua.kiev.prog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.repository.RoleRepository;
import ua.kiev.prog.repository.UserRepository;
import ua.kiev.prog.model.Role;
import ua.kiev.prog.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Adds new user to database.
     */
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    /**
     * Adds new user role to database.
     */
    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    /**
     * Returns user with certain username from database.
     */
    @Transactional(readOnly = true)
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Returns all users with certain username from database.
     */
    @Transactional(readOnly = true)
    public List<User> listUsers(String username) {
        return (List<User>) userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
