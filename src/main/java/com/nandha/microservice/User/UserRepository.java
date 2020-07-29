package com.nandha.microservice.user;
import org.springframework.data.repository.CrudRepository;
import java.util.List;  
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);
}