package com.backend.backend.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get/{query}")
    public ResponseEntity<String> getMethod(@PathVariable("query") String query){
        return ResponseEntity.status(HttpStatus.OK).body(query);
    }


    @PostMapping("/post/{query}")
    public ResponseEntity<String> postMethodName(@PathVariable("query") String query) {
        return ResponseEntity.status(HttpStatus.OK).body(query);
    }
    
}
