package com.example.jpamybatisplusdemo.controller.nacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos/provider")
public class ProviderController {

    @GetMapping("/demo")
    public String provider() {
        return "echo";
    }
}