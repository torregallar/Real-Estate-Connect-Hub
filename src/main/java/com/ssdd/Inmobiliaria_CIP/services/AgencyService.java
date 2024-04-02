package com.ssdd.Inmobiliaria_CIP.services;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Property;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AgencyService {

    private Map<Integer, Agency> agencies = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(0);

    public AgencyService () {}

    public Agency createAgency(Agency agency){

        if (fieldsAreNull(agency)) return null;

        int id = nextId.incrementAndGet();
        agency.setId(id);
        agencies.put(id, agency);

        return agency;
    }

    public List<Agency> getAgencies() {
        return new ArrayList<>(agencies.values());
    }

    public Agency getAgency (int id) {
        return agencies.get(id);
    }

    public Agency deleteAgency (int id) {
        return agencies.remove(id);
    }

    public Agency updateAgency (int id, Agency agency) {
        if (agencies.containsKey(id)) { // Verifies if Map contains given id
            if (fieldsAreNull(agency)) return null;
            agency.setId(id);
            agencies.put(id, agency);
            return agency;
        }
        return null;
    }

    private boolean fieldsAreNull(Agency agency) {
        if ((agency.getName() == null) || (agency.getName().isEmpty()) || (agency.getEmail() == null) || (agency.getEmail().isEmpty()) || (agency.getPhone() == 0) ) { // fields validation
            System.out.println(agency.getEmail() + agency.getName() + agency.getPhone());
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

    public Agency updateAgencyFields(int id, Map<String, Object> fields) {
        if (agencies.containsKey(id)) {
            Agency agencyToUpdate = agencies.get(id);

            if (fields.containsKey("name") && fields.get("name").toString().isEmpty()) {
                return null;
            }

            if (String.valueOf(fields.get("phone")).length() != 9) {
                return null;
            }

            if (!fields.get("email").toString().contains("@")) {
                return null;
            }

            fields.forEach((name, value) -> {
                if (!name.equals("id")) {
                    Field field = ReflectionUtils.findField(Agency.class, name);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, agencyToUpdate, value);
                }
            });
            return agencyToUpdate;
        }
        return null;
    }
}
