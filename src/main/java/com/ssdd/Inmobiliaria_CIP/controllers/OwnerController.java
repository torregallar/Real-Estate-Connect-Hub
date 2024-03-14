package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping("/owners")
    public String getOwners(Model model){
        model.addAttribute("owners", ownerService.getOwners());
        return "owners";
    }

    @GetMapping("/owner/{id}")
    public String getOwner(Model model, @PathVariable int id){
        Owner owner = ownerService.getOwner(id);
        if(owner != null){
            model.addAttribute("owner", owner);
            return "owners-details";
        }
        return "redirect:/owners";
    }

    @PostMapping("/owners/createOwner")
    public String createOwner (Owner owner){
        ownerService.createOwner(owner);
        return "redirect:/owners";
    }
    @GetMapping("/owner/deleteOwner/{id}")
    public String deleteOwner (@PathVariable int id){
        ownerService.deleteOwner(id);
        return "redirect:/owners";
    }

    @GetMapping("/owner/modify/{id}")
    public String modifyOwner(@PathVariable int id, Model model){
        model.addAttribute("owner", ownerService.getOwner(id));
        return "/modify-owner";
    }


    @PostMapping("/owner/modify/{id}")
    public String modifyOwner(@PathVariable int id, Owner owner){
        ownerService.updateOwner(id, owner);
        return "redirect:/owner/{id}";
    }



}
