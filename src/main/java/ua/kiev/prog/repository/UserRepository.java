package ua.kiev.prog.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.User;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);


}
