package com.nandha.microservice;

import org.springframework.boot.SpringApplication;
import com.nandha.microservice.User.*;
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
        User user = new User(firstName, lastName);
        userRepository.save(user);
        return "Completed";
    }
}