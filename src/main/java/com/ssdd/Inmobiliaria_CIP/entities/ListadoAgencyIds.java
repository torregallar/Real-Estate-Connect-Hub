package com.ssdd.Inmobiliaria_CIP.entities;

import java.util.HashSet;
import java.util.Set;

public class ListadoAgencyIds {
    private Set<Integer> agencies;

    private ListadoAgencyIds() {
    }


    public Set<Integer> getAgencies() {
        return agencies;
    }

    public void setAgencies(Set<Integer> agencies) {
        this.agencies = agencies;
    }
}
