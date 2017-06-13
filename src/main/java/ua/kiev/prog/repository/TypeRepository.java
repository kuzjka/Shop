package ua.kiev.prog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.init.ResourceReader;
import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Type;

import java.util.List;
@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {

     Type findByName(String name);



}
