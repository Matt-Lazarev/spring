package com.lazarev.springactuator.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String testMethod(Dto dto){
        return "Test message " + dto.min + " " + dto.max;
    }

}

@Data
class Dto{
    int min;
    int max;
}
