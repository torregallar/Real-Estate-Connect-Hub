package com.ssdd.Inmobiliaria_CIP.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PropertyService {

    private Map<Integer, Property> properties = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(0);

    public PropertyService () {}

    public Property createProperty(Property property) {
        int id = nextId.incrementAndGet();
        property.setId(id);
        properties.put(id, property);

        return property;
    }

    public List<Property> getProperties() {
        return new ArrayList<>(properties.values());
    }

    public Property getProperty(int id) {
        return properties.get(id);
    }

    public Property deleteProperty(int id) {
        return properties.remove(id);
    }

    public Property updateProperty(int id, Property property) {
        if (properties.containsKey(id)) { // Verifies if Map contains given id
            property.setId(id);
            properties.put(id, property);
            return property;
        }
        return null;
    }
}
