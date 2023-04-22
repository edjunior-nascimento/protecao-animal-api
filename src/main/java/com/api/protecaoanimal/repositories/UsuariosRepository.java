package com.api.protecaoanimal.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.protecaoanimal.models.UsuariosModel;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, UUID>{

    Optional<UsuariosModel> findByLogin(String nome);
    
}
