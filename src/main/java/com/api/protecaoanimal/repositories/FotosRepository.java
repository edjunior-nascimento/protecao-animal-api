package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.protecaoanimal.models.FotosModel;

public interface FotosRepository extends JpaRepository<FotosModel, UUID> {
    
}
