package com.nandha.microservice;

import org.springframework.boot.SpringApplication;

import java.util.List;

import com.nandha.microservice.user.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class MainController{
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String index(){
        return "Test";
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
        if (userList.size() == 0){
            return "{}";
        }
        String result = "[";
        for (User n: userList){
            result += (n.toString()  + ",");
        }
        return result.substring(0, result.length() - 1) + "]";
    }
}