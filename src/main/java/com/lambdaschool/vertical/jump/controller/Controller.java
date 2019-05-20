package com.lambdaschool.vertical.jump.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class Controller
{
    @GetMapping("/test")
    public ResponseEntity<?> getHashMap()
    {
        Map<String, String> map = new HashMap<>();
        
        map.put("Testkey", "testvalue");
        map.put("TestKey2", "testvalue2");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
