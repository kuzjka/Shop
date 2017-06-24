package ua.kiev.prog.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String role);

}
