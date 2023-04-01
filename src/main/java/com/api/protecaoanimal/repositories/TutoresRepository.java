package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.TutoresModel;

@Repository
public interface TutoresRepository extends JpaRepository<TutoresModel, UUID>{
    
}
