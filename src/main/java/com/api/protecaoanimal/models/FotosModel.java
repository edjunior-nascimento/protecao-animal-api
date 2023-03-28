package com.api.protecaoanimal.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fotos")
public class FotosModel {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 36)
    private String idAnimal;

    @Column(nullable = false, length = 50)
    private String imagem;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;


}
