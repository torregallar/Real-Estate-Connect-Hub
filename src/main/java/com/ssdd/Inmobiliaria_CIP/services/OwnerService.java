package com.ssdd.Inmobiliaria_CIP.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.repositories.AgencyRepository;
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

    @Autowired
    private AgencyRepository agencyRepository;

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
            optionalOwner.get().setName(owner.getName());
            optionalOwner.get().setLastName(owner.getLastName());
            optionalOwner.get().setEmail(owner.getEmail());
            optionalOwner.get().setPhoneNumber(owner.getPhoneNumber());

            return ownerRepository.save(optionalOwner.get());
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

    @Transactional
    public Owner updatePropertiesOfOwner(int id, Set<Integer> propertyIds) {
        Owner owner = ownerRepository.findById(id).orElse(null);

        if (propertyIds == null) {
            return null;
        }

        if (owner != null) {
            Set <Property> actualProperties = owner.getProperties();
            owner.setProperties(new HashSet<>()); // Initialize properties

            if (propertyIds != null) {

                for (Property actualProperty: actualProperties) {
                    if (!propertyIds.contains(actualProperty.getId())) { // if new properties does not contain some of past properties, these will be deleted (Cause they won't have owner)
                        propertyRepository.delete(actualProperty);
                    }
                }

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


    public Owner updateAgenciesOfOwner(int id, Set<Integer> agenciesIds) {
        Owner owner = ownerRepository.findById(id).orElse(null);
        Set<Owner> owners;

        if (owner != null) {
            if (agenciesIds != null) {
                Set<Agency> actualAgencies = owner.getAgencies();

                for (Agency actualAgency: actualAgencies) {
                    if (!agenciesIds.contains(actualAgency.getId())) {
                        agencyRepository.deleteAgencyOwnerMapping(actualAgency.getId(), id);
                    }
                }

                for (int agencyId: agenciesIds) {
                    Agency agencyAux = agencyRepository.findById(agencyId).orElse(null);

                    if (agencyAux != null) {
                        owners = agencyAux.getOwners(); // Get actual owners of newAgencies
                        owners.add(owner); // Add actual owner to the owner list of the new Agency

                        owner.getAgencies().add(agencyAux); // We update both tables because it does not work if we update just one entity (It should be some H2 restriction)
                        agencyAux.setOwners(owners);
                        agencyRepository.save(agencyAux);
                        ownerRepository.saveAndFlush(owner);

                    }
                }
            }
        }
        return owner;
    }

    public List<Agency> getExistingAgencies() {
        return agencyRepository.findAll();
    }

    public List<Property> getExistingProperties() {
        return propertyRepository.findAll();
    }
}
