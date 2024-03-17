package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgencyController {

    @Autowired
    AgencyService AgencyService;

    @GetMapping("/agencies")
    public String getAgencies (Model model){
        model.addAttribute("agencies", AgencyService.getAgencies());
        return "agency";
    }

    @GetMapping("/agency/{id}")
    public String getAgency (Model model, @PathVariable int id) {
        Agency agency = AgencyService.getAgency(id);
        if (agency != null) {
            model.addAttribute("agency", agency);
            return "agency-details";
        }
        return "redirect:/agency";
    }

    @PostMapping("/agencies/createAgency")
    public String createProperty (Agency agency) {
        AgencyService.createAgency(agency);
        return "redirect:/agency";
    }

    @GetMapping("/agency/deleteAgency/{id}")
    public String deleteAgency(@PathVariable int id) {
        AgencyService.deleteAgency(id);
        return "redirect:/agency";
    }

    @GetMapping("/agency/modify/{id}")
    public String modifyAgency(@PathVariable int id, Model model) {
        model.addAttribute("agency", AgencyService.getAgency(id));
        return "/modify-agency";
    }

    @PostMapping("/agency/modify/{id}")
    public String modifyAgency(@PathVariable int id, Agency agency) {
        AgencyService.updateAgency(id, agency);
        return "redirect:/agency/{id}";
    }
}
