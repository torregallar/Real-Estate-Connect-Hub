package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OwnerRestController {
    @Autowired
    private OwnerService ownerService;

    public OwnerRestController(){}
    @GetMapping
    public ResponseEntity<List<Owner>> getOwners(){
        List<Owner> owners = ownerService.getOwners();
        if(owners.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(owners);
    }

}
