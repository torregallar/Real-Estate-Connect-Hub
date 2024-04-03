package com.ssdd.Inmobiliaria_CIP.services;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
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

        if (fieldsAreNull(owner)) return null;

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
            if (fieldsAreNull(owner)) return null;
            owner.setId(id);
            owners.put(id, owner);
            return owner;
        }
        return null;
    }

    public Owner updateOwnerFields(int id, Map<String, Object> fields){


        if(owners.containsKey(id)){
            Owner ownerToUpdate = owners.get(id);

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
            return ownerToUpdate;
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
