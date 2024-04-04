package com.ssdd.Inmobiliaria_CIP.repositories;

import com.ssdd.Inmobiliaria_CIP.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaParametersParameterAccessor;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
