package com.ssdd.Inmobiliaria_CIP.repositories;

import com.ssdd.Inmobiliaria_CIP.entities.Agency;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM agency_owner_map WHERE AGENCY_ID = :agencyId AND OWNER_ID = :ownerId", nativeQuery = true)
    void deleteAgencyOwnerMapping(@Param("agencyId") int agencyId, @Param("ownerId") int ownerId);


}
