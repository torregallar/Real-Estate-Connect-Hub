package com.ssdd.Inmobiliaria_CIP.services;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OwnerService {
    private Map<Integer, Owner> owners = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(0);
    public OwnerService(){}

    public Owner createOwner(Owner owner){
        int id = nextId.incrementAndGet();
        owner.setId(id);
        owners.put(id, owner);
        return owner;
    }

    public List<Owner> getOwners(){
        return new ArrayList<>(owners.values());
    }

}
