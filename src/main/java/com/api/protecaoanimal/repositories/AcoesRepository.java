package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.AcoesModel;

@Repository
public interface AcoesRepository extends JpaRepository<AcoesModel, UUID>{
    
}
