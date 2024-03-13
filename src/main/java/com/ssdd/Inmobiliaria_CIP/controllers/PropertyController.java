package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@Controller
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/properties")
    public String getProperties (Model model){
        model.addAttribute("properties", propertyService.getProperties());
        return "properties";
    }

    @GetMapping("/property/{id}")
    public String getProperty (Model model, @PathVariable int id) {
        Property property = propertyService.getProperty(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "property-details";
        }
        return "redirect:/properties";
    }

    @PostMapping("/properties/createProperty")
    public String createProperty (Property property) {
        propertyService.createProperty(property);
        return "redirect:/properties";
    }

    @GetMapping("/property/deleteProperty/{id}")
    public String deleteProperty(@PathVariable int id) {
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }

    @GetMapping("/property/modify/{id}")
    public String modifyProperty(@PathVariable int id, Model model) {
        model.addAttribute("property", propertyService.getProperty(id));
        return "/modify-property";
    }

    @PostMapping("/property/modify/{id}")
    public String modifyProperty(@PathVariable int id, Property property) {
        propertyService.updateProperty(id, property);
        return "redirect:/property/{id}";
    }

}
