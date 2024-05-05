package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/owner/modify/{id}/modifyAgencies")
    public String getOwnerModifyAgencies(Model model, @PathVariable int id) {
        Owner owner = ownerService.getOwner(id);
        List<Agency> existingAgencies = ownerService.getExistingAgencies();
        if (owner != null) {
            model.addAttribute("owner", owner);
            model.addAttribute("existingAgencies", existingAgencies);
            return "owner-modify-agencies";
        }
        return "redirect:/owners";
    }

    @GetMapping("/owner/modify/{id}/modifyAgenciesOfOwner")
    public String modifyAgenciesOfOwner(@PathVariable int id, @RequestParam(name = "ids") Set<Integer> agenciesIds, Model model) {
        Owner owner = ownerService.getOwner(id);
        if (owner != null) {
            if (!agenciesIds.isEmpty()) {
                ownerService.updateAgenciesOfOwner(id, agenciesIds);
            } else {
                ownerService.updateAgenciesOfOwner(id, new HashSet<>());
            }

            return "redirect:/owner/" + id;
        }

        return "redirect:/owners";
    }


    @GetMapping("/owner/modify/{id}/modifyProperties")
    public String getOwnerModifyProperties(Model model, @PathVariable int id) {
        Owner owner = ownerService.getOwner(id);
        List<Property> existingProperties = ownerService.getExistingProperties();
        if (owner != null) {
            model.addAttribute("owner", owner);
            model.addAttribute("existingProperties", existingProperties);
            return "owner-modify-properties";
        }
        return "redirect:/owners";
    }

    @GetMapping("/owner/modify/{id}/modifyPropertiesOfOwner")
    public String modifyPropertiesOfOwner(@PathVariable int id, @RequestParam(name = "ids") Set<Integer> propertiesIds, Model model) {
        Owner owner = ownerService.getOwner(id);
        if (owner != null) {
            if (!propertiesIds.isEmpty()) {
                ownerService.updatePropertiesOfOwner(id, propertiesIds);
            } else {
                ownerService.updatePropertiesOfOwner(id, new HashSet<>());
            }

            return "redirect:/owner/" + id;
        }

        return "redirect:/owners";
    }
}
