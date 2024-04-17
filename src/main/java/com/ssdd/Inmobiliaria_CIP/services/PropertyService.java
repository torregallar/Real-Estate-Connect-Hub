package com.ssdd.Inmobiliaria_CIP.services;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
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
        Owner owner;

        if (fieldsAreNull(property)) return null;

        property.setId(0);

        owner = ownerRepository.findById(property.getOwner().getId()).orElse(null);

        if (owner != null) { // The owner of this property exists, so we can create the property
            property.setOwner(owner);
            return propertyRepository.save(property);
        }

        return null;
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
        Owner owner;
        Optional<Property> optionalProperty = propertyRepository.findById(id);

        if (optionalProperty.isPresent()) {
            if (fieldsAreNull(property)) return null;
            property.setId(id);

            owner = ownerRepository.findById(property.getOwner().getId()).orElse(null);

            if (owner != null) { // The owner of this property exists, so we can create the property
                return propertyRepository.save(property);
            }
        }
        return null;
    }

    public Property updatePropertyFields(int id, Map<String, Object> fields) { // Patch function
        Property propertyToUpdate = propertyRepository.findById(id).orElse(null);
        Owner owner;
        int ownerId;

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

            if (fields.containsKey("owner")) {
                Field field = ReflectionUtils.findField(Property.class, "owner");
                HashMap<String, Object> ownerFields;

                field.setAccessible(true);

                ownerFields = (HashMap<String, Object>)fields.get("owner"); // Get owner field of property as a HashMap

                if (!ownerFields.isEmpty()) {
                    ownerId = (int)(ownerFields).get("id"); // Get id of field owner in new property object

                    owner = ownerRepository.findById(ownerId).orElse(null); // Get the real owner object

                    if (owner != null) { // If owner exists
                        ReflectionUtils.setField(field, propertyToUpdate, owner); // Change owner of property
                    } else {
                        return null;
                    }
                }
            }
            return propertyRepository.save(propertyToUpdate);
        }
        return null;
    }

    private boolean fieldsAreNull(Property property) {
        if ((property.getName() == null) || (property.getName().isEmpty()) || (property.getPrice() <= 0.0)
                || (property.getType() == null) || (property.getType().isEmpty())
                || (property.getRooms() <= 0) || (property.getBathrooms() <= 0) || (property.getSqMetres() <= 0.0)
                || (property.getAddress() == null) || (property.getAddress().isEmpty())
                || (property.getDescription() == null) || (property.getDescription().isEmpty())
                || (property.getOwner() == null))  { // fields validation
            return true;
        }
        return false;
    }
}
