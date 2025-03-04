package com.ssdd.Inmobiliaria_CIP.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "errorPage.html";
    }

    public String getErrorPath() {
        return "/error";
    }

}
