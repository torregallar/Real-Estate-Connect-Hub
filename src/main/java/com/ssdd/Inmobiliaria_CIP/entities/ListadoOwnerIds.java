package com.ssdd.Inmobiliaria_CIP.entities;

import java.util.HashSet;
import java.util.Set;

public class ListadoOwnerIds {
    private Set<Integer> owners;

    private ListadoOwnerIds() {
    }


    public Set<Integer> getOwners() {
        return owners;
    }

    public void setOwners(Set<Integer> owners) {
        this.owners = owners;
    }
}
