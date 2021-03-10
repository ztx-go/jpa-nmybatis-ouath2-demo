package com.example.jpamybatisplusdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test01")
    public String test01() throws Exception {
        return "success";
    }

}
