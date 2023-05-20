package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/os")
public class AppController {

    @GetMapping("/getpage")
    public String m1() {

        RestTemplate rt = new RestTemplate();
        String rtUrl = "http://localhost:8082/page1";
        ResponseEntity<String> response = rt.getForEntity(rtUrl, String.class);
        return "response: " + response.getBody() + "";

    }

   

    @GetMapping("/getpeople")
    public String m2() {

        RestTemplate rt = new RestTemplate();
        String rtUrl = "http://localhost:8082/getuser";
        String response = rt.getForObject(rtUrl, String.class);
        return "people list: " + response;

    }

    @GetMapping("/getpeople2")
    public Object m2Json() {
        RestTemplate rt = new RestTemplate();
        String rtUrl = "http://localhost:8082/getuser";
        Object response = rt.getForObject(rtUrl, Object.class);
        return response;

    }

}
