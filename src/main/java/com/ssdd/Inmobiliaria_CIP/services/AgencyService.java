package com.ssdd.Inmobiliaria_CIP.services;

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

@Service
public class AgencyService {

    private Map<Integer, Agency> agencies = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(0);

    public AgencyService () {}

    public Agency createAgency(Agency agency){
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
            agency.setId(id);
            agencies.put(id, agency);
            return agency;
        }
        return null;
    }

    public Agency updateAgencyFields(int id, Map<String, Object> fields) {
        Agency agencyToUpdate = agencies.get(id);
        if (agencyToUpdate != null) {
            fields.forEach((name, value)-> {
                Field field = ReflectionUtils.findField(Property.class, name);
                field.setAccessible(true);
                ReflectionUtils.setField(field, agencyToUpdate, value);
            });
            agencies.put(id, agencyToUpdate);
            return agencyToUpdate;
        }
        return null;
    }
}
