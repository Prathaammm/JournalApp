package com.prathm.App.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healtchk {
    @GetMapping("/health")
    public String healtchk(){
        return "ok!";
    };

}
