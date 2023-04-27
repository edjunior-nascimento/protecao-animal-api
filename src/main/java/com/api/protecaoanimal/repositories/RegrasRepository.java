package com.api.protecaoanimal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.RegrasModel;

@Repository
public interface RegrasRepository extends JpaRepository<RegrasModel, UUID>{
    boolean existsByNome(String nome);
}
