package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping
    public String getProperties (Model model){
        model.addAttribute("properties", propertyService.getProperties());
        return "properties";
    }

    @GetMapping("/{id}")
    public String getProperty (Model model, @PathVariable int id) {
        Property property = propertyService.getProperty(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "property-details";
        }
        return "redirect/properties";
    }
}
