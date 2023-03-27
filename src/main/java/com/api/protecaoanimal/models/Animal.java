package com.api.protecaoanimal.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 50)
    private String nome;

    @Column()
    private int especie;

    @Column()
    private int sexo;
    
    @Column()
    private int idade;
    
    @Column()
    private int porte;
    
    @Column(length = 50)
    private String raca;
    
    @Column(length = 255)
    private String temperamento;
    
    @Column(length = 255)
    private String situacao;
    
    @Column()
    private String historia;
    
    @Column(length = 255)
    private String fotos;
    
}
