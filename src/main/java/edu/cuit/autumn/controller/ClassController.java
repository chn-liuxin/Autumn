package edu.cuit.autumn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClassController {

    @RequestMapping("/test")
    public String test() {
        return "/page/test";
    }

}
