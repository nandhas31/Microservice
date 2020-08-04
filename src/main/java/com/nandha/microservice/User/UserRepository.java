package com.nandha.microservice.user;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
public interface UserRepository extends CrudRepository<User, Integer>{
    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    void deleteByFirstNameAndLastNameAndId(String firstName, String lastName, int id);
    
    User findOneByFirstNameAndLastNameAndId(String firstName, String lastName, int id);
}