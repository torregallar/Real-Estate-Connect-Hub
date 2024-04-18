package com.ssdd.Inmobiliaria_CIP.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.repositories.OwnerRepository;
import com.ssdd.Inmobiliaria_CIP.repositories.PropertyRepository;
import jakarta.transaction.Transactional;
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

        if (fieldsAreNull(owner)) return null;

        owner.setId(0);

        return ownerRepository.save(owner);
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

    /*public Owner updatePropertiesOfOwner(int id, Set<Integer> propertyIds) {
        Owner owner = ownerRepository.findById(id).orElse(null);


        if (owner != null) {
            owner.setProperties(new HashSet<>()); // Initialize properties

            if (propertyIds != null) {
                for (int propertyId: propertyIds) {
                    Property propertyAux = propertyRepository.findById(propertyId).orElse(null);

                    if (propertyAux != null) {

                        owner.getProperties().add(propertyAux);
                        propertyAux.setOwner(owner);
                        propertyRepository.save(propertyAux);
                        ownerRepository.save(owner);

                    }
                }
            }
        }
        return owner;
    }*/

    @Transactional
    public Owner updatePropertiesOfOwner(int id, Set<Integer> propertyIds) {
        Owner owner = ownerRepository.findById(id).orElse(null);


        if (owner != null) {
            owner.setProperties(new HashSet<>()); // Initialize properties

            if (propertyIds != null) {
                for (int propertyId: propertyIds) {
                    Property propertyAux = propertyRepository.findById(propertyId).orElse(null);

                    if (propertyAux != null) {

                        owner.getProperties().add(propertyAux);
                        propertyAux.setOwner(owner);
                        propertyRepository.save(propertyAux);
                        ownerRepository.save(owner);

                    }
                }
            }
        }
        return owner;
    }
}
