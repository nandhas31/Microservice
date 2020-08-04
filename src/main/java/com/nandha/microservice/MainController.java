package com.nandha.microservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.List;
import com.nandha.microservice.user.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.*;
@RestController
@RequestMapping("/")
public class MainController{
    @Autowired
    private UserRepository userRepository;

    Gson gson = new Gson();
    @GetMapping("/")
    public String index(){
        return "Test";
    }
    @GetMapping("/all")
    public String all(){
        return userRepository.findAll().toString();
    }
    @PostMapping("/add")
    public String add(@RequestParam String firstName, @RequestParam String lastName){
        if (firstName == null || lastName == null){
            return "Failed";
        }
        User user = new User(firstName, lastName);
        userRepository.save(user);
        return "Completed";
    }
    @PostMapping("/search")
    public String search(@RequestParam String firstName, @RequestParam String lastName){
        List<User> userList = userRepository.findAllByFirstNameAndLastName(firstName, lastName);
        if (userList.isEmpty()){
            return "{}";
        }
        return gson.toJson(userList);
    }
    @PostMapping("/findone")
    public String findOne(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int id){
        User user = userRepository.findOneByFirstNameAndLastNameAndId(firstName, lastName, id);
        return gson.toJson(user);
    }
    @PostMapping("/delete")
    public String search(@RequestParam String firstName,  @RequestParam String lastName, @RequestParam int id){
            User user = userRepository.findOneByFirstNameAndLastNameAndId(firstName, lastName, id);
            if (user == null){
                return "User not found";
            }
            try{
            userRepository.deleteByFirstNameAndLastNameAndId(firstName, lastName, id);
            }
            catch(RuntimeException e){
                return "Error";
            }
            return "Done";
    }
}