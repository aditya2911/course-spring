package com.example.coursespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllers {

    @Autowired
    MongoRepositoryTest mt;
    @GetMapping("/")
    public String test(){
        return "adityaaaa";
    }

    @GetMapping("/test")
    public String push(){
        testMongo t = new testMongo("1","Aditya");

        mt.save(t);

        return "Success";

    }

}
