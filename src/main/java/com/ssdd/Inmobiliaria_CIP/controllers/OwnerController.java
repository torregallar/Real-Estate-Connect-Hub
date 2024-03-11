package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping
    public String getOwners(Model model){
        model.addAttribute("owners", ownerService.getOwners());
        return "owners";
    }

    @GetMapping("/{id}")
    public String getOwner(Model model, @PathVariable int id){
        Owner owner = ownerService.getOwner(id);
        if(owner != null){
            model.addAttribute("owner", owner);
            return "owner-details";
        }
        return "redirect/owners";
    }
}
