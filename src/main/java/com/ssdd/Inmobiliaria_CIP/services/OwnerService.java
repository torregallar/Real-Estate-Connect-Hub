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

    public Owner getOwner(int id){
        return owners.get(id);
    }

    public Owner deleteOwner(int id){
        return owners.remove(id);
    }

    public Owner updateOwner(int id, Owner owner) {
        if (owners.containsKey(id)) {
            owner.setId(id);
            owners.put(id, owner);
            return owner;
        }
        return null;
    }

}
