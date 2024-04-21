package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.OwnerId;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

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
        List<Owner> existingOwners = propertyService.getExistingOwners();
        if (property != null) {
            model.addAttribute("property", property);
            model.addAttribute("existingOwners", existingOwners);
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

    @GetMapping("/property/modify/{id}/modifyOwner/{ownerId}")
    public String modifyOwnerOfProperty(@PathVariable int id, @PathVariable int ownerId, Model model) {
        propertyService.updateOwnerOfProperty(id, new OwnerId(ownerId));
        model.addAttribute("property", propertyService.getProperty(id));
        model.addAttribute("existingOwners", propertyService.getExistingOwners());
        return "/property-details";
    }

}
