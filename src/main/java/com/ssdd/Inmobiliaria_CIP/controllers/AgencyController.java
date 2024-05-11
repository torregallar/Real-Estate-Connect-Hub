package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.ListadoOwnerIds;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.repositories.AgencyRepository;
import com.ssdd.Inmobiliaria_CIP.services.AgencyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AgencyController {

    @Autowired
    AgencyService agencyService;

    @GetMapping("/agencies")
    public String getAgencies (Model model){
        model.addAttribute("agencies", agencyService.getAgencies());
        return "agency";
    }

    @GetMapping("/agency/{id}")
    public String getAgency (Model model, @PathVariable int id) {
        Agency agency = agencyService.getAgency(id);
        if (agency != null) {
            model.addAttribute("agency", agency);
            return "agency-details";
        }
        return "redirect:/agencies";
    }

    @PostMapping("/agencies/createAgency")
    public String createProperty (Agency agency) {
        agencyService.createAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping("/agency/deleteAgency/{id}")
    public String deleteAgency(@PathVariable int id) {
        agencyService.deleteAgency(id);
        return "redirect:/agencies";
    }

    @GetMapping("/agency/modify/{id}")
    public String modifyAgency(@PathVariable int id, Model model) {
        model.addAttribute("agency", agencyService.getAgency(id));
        return "modify-agency";
    }

    @PostMapping("/agency/modify/{id}")
    public String modifyAgency(@PathVariable int id, Agency agency) {
        agencyService.updateAgency(id, agency);
        return "redirect:/agency/{id}";
    }

    @GetMapping("/agency/modify/{id}/modifyOwners")
    public String getAgencyModifyOwners (Model model, @PathVariable int id) {
        Agency agency = agencyService.getAgency(id);
        List<Owner> existingOwners = agencyService.getExistingOwners();
        if (agency != null) {
            model.addAttribute("agency", agency);
            model.addAttribute("existingOwners", existingOwners);
            return "agency-modify-owners";
        }
        return "redirect:/agencies";
    }

    @GetMapping("/agency/modify/{id}/modifyOwnersOfAgency")
    public String modifyOwnersOfAgency(@PathVariable int id, @RequestParam(name = "ids") Set<Integer> ownerIds) {
        Agency agency = agencyService.getAgency(id);
        if (agency != null) {
            if (!ownerIds.isEmpty()) {
                agencyService.updateOwnersOfAgency(id, ownerIds);
            } else {
                agencyService.updateOwnersOfAgency(id, new HashSet<>());
            }

            return "redirect:/agency/" + id;
        }

        return "redirect:/agencies";

    }

}
