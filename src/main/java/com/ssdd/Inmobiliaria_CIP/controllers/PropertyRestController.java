package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api/properties")
public class PropertyRestController {

    @Autowired
    private PropertyService propertyService;

    public PropertyRestController () {}

    @GetMapping
    public ResponseEntity<List<Property>> getProperties() {
        List<Property> properties = propertyService.getProperties();
        if (properties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable int id) {
        Property property = propertyService.getProperty(id);
        if (property != null) {
            return ResponseEntity.ok(property);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property property1 = propertyService.createProperty(property);
        if (property1 != null) {
            return ResponseEntity.ok(property1);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Property> deleteProperty(@PathVariable int id) {
        if (propertyService.deleteProperty(id) != null) {
            return ResponseEntity.noContent().build(); // Es lo mismo que un .status(204)
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable int id, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Property> updatePropertyFields(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        Property updatedProperty = propertyService.updatePropertyFields(id, fields);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        }
        return ResponseEntity.notFound().build();
    }


}
