package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.AnunciosModel;

@Repository
public interface AnunciosRepository extends JpaRepository<AnunciosModel, UUID>{
    
}
