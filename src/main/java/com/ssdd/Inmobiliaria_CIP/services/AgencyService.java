package com.ssdd.Inmobiliaria_CIP.services;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.repositories.AgencyRepository;
import com.ssdd.Inmobiliaria_CIP.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AgencyService {


    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public AgencyService () {}

    public Agency createAgency(Agency agency){

        if (fieldsAreNull(agency)) return null;
        agency.setId(0);

        return agencyRepository.save(agency);
    }

    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    public Agency getAgency (int id) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);

        return optionalAgency.orElse(null); // returns optional value, or returns null if it is empty,
    }

    public Agency deleteAgency (int id) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);
        if (optionalAgency.isPresent()) {
            Set<Owner> ownersOfAgency = optionalAgency.get().getOwners();

            for (Owner owner: ownersOfAgency) {
                owner.getAgencies().remove(optionalAgency.get());
                //agencyRepository.deleteAgencyOwnerMapping(id, owner.getId()); // We need to delete relations with owner to complete deletion correctly
            }
            agencyRepository.deleteById(id);
        }
        return optionalAgency.orElse(null);
    }

    public Agency updateAgency (int id, Agency agency) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);
        if (optionalAgency.isPresent()) {
            if (fieldsAreNull(agency)) return null;
            optionalAgency.get().setName(agency.getName());
            optionalAgency.get().setEmail(agency.getEmail());
            optionalAgency.get().setPhone(agency.getPhone());

            return agencyRepository.save(optionalAgency.get());
        }
        return null;
    }



    public Agency updateAgencyFields(int id, Map<String, Object> fields) {
        Agency agencyToUpdate = agencyRepository.findById(id).orElse(null);

        if (agencyToUpdate != null) {

            if (fields.containsKey("name") && fields.get("name").toString().isEmpty()) {
                return null;
            }

            if (fields.containsKey("phone") && String.valueOf(fields.get("phone")).length() != 9) {
                return null;
            }

            if (fields.containsKey("email") && !fields.get("email").toString().contains("@")) {
                return null;
            }

            fields.forEach((name, value) -> {
                if (!name.equals("id") && (!name.equals("owners"))) {
                    Field field = ReflectionUtils.findField(Agency.class, name);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, agencyToUpdate, value);
                }
            });
            return agencyRepository.save(agencyToUpdate);
        }
        return null;
    }




    private boolean fieldsAreNull(Agency agency) {
        if ((agency.getName() == null) || (agency.getName().isEmpty()) || (agency.getEmail() == null) || (agency.getEmail().isEmpty()) || (agency.getPhone() == 0) ) { // fields validation
            return true;
        }

        if (String.valueOf(agency.getPhone()).length() != 9) {
            return true;
        }

        if (!agency.getEmail().contains("@")) {
            return true;
        }
        return false;
    }

    public Agency updateOwnersOfAgency(int id, Set<Integer> ownersIds) {
        Agency agency = agencyRepository.findById(id).orElse(null);
        Set<Agency> agencies;

        if (agency != null) {
            if (ownersIds != null) {
                Set<Owner> actualOwners = agency.getOwners();

                for (Owner actualOwner: actualOwners) {
                    if (!ownersIds.contains(actualOwner.getId())) {
                        agencyRepository.deleteAgencyOwnerMapping(id, actualOwner.getId());
                    }
                }

                for (int ownerId: ownersIds) {
                    Owner ownerAux = ownerRepository.findById(ownerId).orElse(null);

                    if (ownerAux != null) {
                        agencies = ownerAux.getAgencies();
                        agencies.add(agency);

                        agency.getOwners().add(ownerAux);
                        ownerAux.setAgencies(agencies);

                        ownerRepository.save(ownerAux);
                        agencyRepository.save(agency);

                    }
                }
            }
        }

        return agency;

    }

    public List<Owner> getExistingOwners() {
        return ownerRepository.findAll();
    }
}
