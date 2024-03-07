package com.ssdd.Inmobiliaria_CIP.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PropertyService {

    private Map<Long, Property> properties = new HashMap<>();
    private AtomicLong nextId = new AtomicLong(0);

    public PropertyService () {}

    public Property createProperty(Property property) {
        long id = nextId.incrementAndGet();
        property.setId(id);
        properties.put(id, property);

        return property;
    }

    public List<Property> getProperties() {
        return new ArrayList<>(properties.values());
    }

    public Property getProperty(Long id) {
        return properties.get(id);
    }

    public Property deleteProperty(Long id) {
        return properties.remove(id);
    }


}
