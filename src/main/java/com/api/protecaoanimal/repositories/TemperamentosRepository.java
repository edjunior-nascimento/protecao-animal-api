package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.TemperamentosModel;

@Repository
public interface TemperamentosRepository extends JpaRepository<TemperamentosModel, UUID>{
    boolean existsByNome(String nome);
    
    
}
