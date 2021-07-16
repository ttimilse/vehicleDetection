package com.example.vehicledetection.controller;


import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RepositoryRestController
@RequestMapping("/home")
public class TestController{
    @GetMapping

    public ResponseEntity test(){
        HashMap<String, Integer> wordCount = new HashMap<>();
         wordCount.put("apple",1);
        return new ResponseEntity(wordCount, HttpStatus.OK);

    }
}
