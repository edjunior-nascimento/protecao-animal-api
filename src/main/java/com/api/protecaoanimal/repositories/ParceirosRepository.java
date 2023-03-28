package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.ParceirosModel;

@Repository
public interface ParceirosRepository extends JpaRepository<ParceirosModel, UUID>{
    
}
