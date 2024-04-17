package com.ssdd.Inmobiliaria_CIP.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ssdd.Inmobiliaria_CIP.entities.Agency;
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

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public OwnerService(){}

    public Owner createOwner(Owner owner){
        Set<Property> propertySet = owner.getProperties();
        List<Property> realPropertyList = propertyRepository.findAll();

        if (fieldsAreNull(owner)) return null;

        owner.setId(0);

        // System.out.println(owner.getProperties().toString());

        for (Property property: propertySet) {
            for (Property newProperty: realPropertyList) {
                if (property.getName().equals(newProperty.getName())) {
                    owner.getProperties().remove(property);
                    owner.getProperties().add(newProperty);
                }
            }
        }

        ownerRepository.save(owner); // Even tough owner has no properties, we can create owners

        return owner;
    }

    public List<Owner> getOwners(){
        return ownerRepository.findAll();
    }

    public Owner getOwner(int id){
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        return optionalOwner.orElse(null);
    }

    public Owner deleteOwner(int id){
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            ownerRepository.deleteById(id);
        }
        return optionalOwner.orElse(null);
    }

    public Owner updateOwner(int id, Owner owner) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            if (fieldsAreNull(owner)) return null;
            owner.setId(id);

            return ownerRepository.save(owner);
        }
        return null;
    }

    public Owner updateOwnerFields(int id, Map<String, Object> fields){
        Owner ownerToUpdate = ownerRepository.findById(id).orElse(null);

        if(ownerToUpdate != null){

            if (fields.containsKey("name") && fields.get("name").toString().isEmpty()) {
                return null;
            }

            if (fields.containsKey("lastName") && fields.get("lastName").toString().isEmpty()) {
                return null;
            }

            if (fields.containsKey("phoneNumber") && (String.valueOf(fields.get("phoneNumber")).length() != 9 || (long)fields.get("phoneNumber")<=0)) {
                return null;
            }

            if (fields.containsKey("email") && !fields.get("email").toString().contains("@")) {
                return null;
            }

            fields.forEach((name,value)->{
                if (!name.equals("id")) {
                    Field field = ReflectionUtils.findField(Owner.class, name);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, ownerToUpdate, value);
                }
            });
            return ownerRepository.save(ownerToUpdate);
        }
        return null;
    }

    private boolean fieldsAreNull(Owner owner) {
        if ((owner.getName() == null) || (owner.getName().isEmpty()) || (owner.getLastName() == null)
            || (owner.getLastName().isEmpty()) || (owner.getEmail() == null) || (owner.getEmail().isEmpty())
                || (owner.getPhoneNumber() <= 0)) { // fields validation
                    return true;
        }

        if (String.valueOf(owner.getPhoneNumber()).length() != 9) {
            return true;
        }

        if (!owner.getEmail().contains("@")) {
            return true;
        }
        return false;
    }
}
