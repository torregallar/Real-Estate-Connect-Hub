package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.ListadoOwnerIds;
import com.ssdd.Inmobiliaria_CIP.entities.ListadoPropertyIds;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/agencies")
public class AgencyRestController {

    @Autowired
    private AgencyService agencyService;

    public AgencyRestController () {}

    @GetMapping
    public ResponseEntity<List<Agency>> getAgencies() {
        List<Agency> agencies = agencyService.getAgencies();
        if (agencies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agencies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agency> getAgency(@PathVariable int id) {
        Agency agency = agencyService.getAgency(id);
        if (agency != null) {
            return ResponseEntity.ok(agency);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Agency> createAgency(@RequestBody Agency agency) {
        Agency agency1 = agencyService.createAgency(agency);
        if (agency1 != null) {
            return ResponseEntity.ok(agency1);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Agency> deleteAgency(@PathVariable int id) {
        if (agencyService.deleteAgency(id) != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agency> updateAgency(@PathVariable int id, @RequestBody Agency agency) {
        Agency updatedAgency = agencyService.updateAgency(id, agency);
        if (updatedAgency != null) {
            return ResponseEntity.ok(updatedAgency);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Agency> updateAgencyFields(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        Agency updatedAgency = agencyService.updateAgencyFields(id, fields);
        if (updatedAgency != null) {
            return ResponseEntity.ok(updatedAgency);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/owners")
    public ResponseEntity<Set<Owner>> getAgenciesOfOwner(@PathVariable int id){
        Agency agency = agencyService.getAgency(id);
        if(agency != null){
            Set<Owner> owners = agency.getOwners();
            if (owners != null && !owners.isEmpty()) {
                return ResponseEntity.ok(owners);
            } else {
                return ResponseEntity.ok(new HashSet<>());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/owners")
    public ResponseEntity<Agency> updateOwnersOfAgency(@PathVariable int id, @RequestBody ListadoOwnerIds newOwners) {

        if (newOwners.getOwners() == null) {
            return ResponseEntity.badRequest().build();
        }

        Agency updatedAgency = agencyService.updateOwnersOfAgency(id, newOwners.getOwners());
        if (updatedAgency != null) {
            return ResponseEntity.ok(agencyService.getAgency(id));
        }

        return ResponseEntity.badRequest().build();
    }

}
