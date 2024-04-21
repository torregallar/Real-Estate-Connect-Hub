package com.ssdd.Inmobiliaria_CIP.services;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.OwnerId;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.repositories.OwnerRepository;
import com.ssdd.Inmobiliaria_CIP.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public PropertyService () {}

    public Property createProperty(Property property) {

        if (fieldsAreNull(property)) return null;

        property.setId(0);

        return propertyRepository.save(property);
    }

    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    public Property getProperty(int id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);

        return optionalProperty.orElse(null);
    }

    public Property deleteProperty(int id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteById(id);
        }
        return optionalProperty.orElse(null);
    }

    public Property updateProperty(int id, Property property) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);

        if (optionalProperty.isPresent()) {
            if (fieldsAreNull(property)) return null;
            property.setId(id);
            property.setOwner(optionalProperty.get().getOwner()); // We need to save the past owner

            return propertyRepository.save(property);
        }
        return null;
    }

    public Property updatePropertyFields(int id, Map<String, Object> fields) { // Patch function
        Property propertyToUpdate = propertyRepository.findById(id).orElse(null);

        if (propertyToUpdate != null) {

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
                if (!name.equals("id") && !name.equals("owner")) {
                    Field field = ReflectionUtils.findField(Property.class, name);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, propertyToUpdate, value);
                }
            });
            return propertyRepository.save(propertyToUpdate);
        }
        return null;
    }

    public Property updateOwnerOfProperty(int id, OwnerId ownerId) {
        Owner owner = ownerRepository.findById(ownerId.getOwner()).orElse(null);
        Property property = propertyRepository.findById(id).orElse(null);

        if (owner != null && property != null) {
            property.setOwner(owner);
            return propertyRepository.save(property);
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


    public List<Owner> getExistingOwners() {
        return ownerRepository.findAll();
    }
}
