package com.ssdd.Inmobiliaria_CIP.repositories;

import com.ssdd.Inmobiliaria_CIP.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
