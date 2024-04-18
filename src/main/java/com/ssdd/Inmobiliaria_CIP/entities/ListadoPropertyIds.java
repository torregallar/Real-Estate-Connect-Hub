package com.ssdd.Inmobiliaria_CIP.entities;

import java.util.HashSet;
import java.util.Set;

public class ListadoPropertyIds {
    private Set<Integer> properties = new HashSet<>();

    private ListadoPropertyIds() {
    }


    public Set<Integer> getProperties() {
        return properties;
    }

    public void setProperties(Set<Integer> properties) {
        this.properties = properties;
    }
}
