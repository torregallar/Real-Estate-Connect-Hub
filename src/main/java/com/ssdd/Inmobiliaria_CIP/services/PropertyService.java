package com.ssdd.Inmobiliaria_CIP.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
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

        if (fieldsAreNull(property)) return null;

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
            if (fieldsAreNull(property)) return null;
            property.setId(id);
            properties.put(id, property);
            return property;
        }
        return null;
    }

    public Property updatePropertyFields(int id, Map<String, Object> fields) { // Patch function
        if (properties.containsKey(id)) {
            Property propertyToUpdate = properties.get(id);

            if (fields.containsKey("name") && fields.get("name").toString().isEmpty()) {
                return null;
            }

            if (fields.containsKey("price") && (double)fields.get("price") < 0.0) {
                return null;
            }

            if (fields.containsKey("type") && fields.get("type").toString().isEmpty()) {
                return null;
            }

            if (fields.containsKey("rooms") && (int)fields.get("rooms") < 0) {
                return null;
            }

            if (fields.containsKey("bathrooms") && (int)fields.get("bathrooms") < 0) {
                return null;
            }

            if (fields.containsKey("sqMetres") && (double)fields.get("sqMetres") < 0.0) {
                return null;
            }

            if (fields.containsKey("adress") && fields.get("adress").toString().isEmpty()) {
                return null;
            }

            if (fields.containsKey("description") && fields.get("description").toString().isEmpty()) {
                return null;
            }

            fields.forEach((name, value)-> {
                if (!name.equals("id")) {
                    Field field = ReflectionUtils.findField(Property.class, name);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, propertyToUpdate, value);
                }
            });
            return propertyToUpdate;
        }
        return null;
    }

    private boolean fieldsAreNull(Property property) {
        if ((property.getName() == null) || (property.getName().isEmpty()) || (property.getPrice() <= 0.0)
                || (property.getType() == null) || (property.getType().isEmpty())
                || (property.getRooms() <= 0) || (property.getBathrooms() <= 0) || (property.getSqMetres() <= 0.0)
                || (property.getAddress() == null) || (property.getAddress().isEmpty())
                || (property.getDescription() == null) || (property.getDescription().isEmpty()))  { // fields validation
            return true;
        }
        return false;
    }
}
