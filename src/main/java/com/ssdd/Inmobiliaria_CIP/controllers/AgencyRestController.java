package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

}
