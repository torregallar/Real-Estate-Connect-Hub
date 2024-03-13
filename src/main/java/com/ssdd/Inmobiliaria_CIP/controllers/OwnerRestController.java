package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/owners")
public class OwnerRestController {
    @Autowired
    private OwnerService ownerService;

    public OwnerRestController(){}

    @GetMapping
    public ResponseEntity<List<Owner>> getOwner(){
        List<Owner> owners = ownerService.getOwners();
        if(owners.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(owners);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable int id){
        Owner owner = ownerService.getOwner(id);
        if(owner != null){
            return ResponseEntity.ok(owner);
        }
        return ResponseEntity.notFound().build();
    }
   @PostMapping
   public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        Owner owner1 = ownerService.createOwner(owner);
        if(owner1 != null){
            return ResponseEntity.ok(owner1);
       }
        return ResponseEntity.badRequest().build();
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable int id){
        if(ownerService.deleteOwner(id) != null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
   }
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable int id, @RequestBody Owner owner) {
        Owner updatedOwner = ownerService.updateOwner(id, owner);
        if (updatedOwner != null) {
            return ResponseEntity.ok(updatedOwner);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Owner> updateOwnerFields(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        Owner updatedOwner = ownerService.updateOwnerFields(id, fields);
        if (updatedOwner != null) {
            return ResponseEntity.ok(updatedOwner);
        }
        return ResponseEntity.notFound().build();
    }


}
