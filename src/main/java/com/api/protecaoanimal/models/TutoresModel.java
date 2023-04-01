package com.api.protecaoanimal.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutores")
public class TutoresModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutores")
    private List<AnimaisModel> animais;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "tutores")
    private CastracoesModel castrado;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "tutores")
    private AdocoesModel adocao;

    @Column(length = 45)
    private String nome;

    @Column(length = 45)
    private String telefone;

    @Column(length = 45)
    private String email;

    @Column(length = 20)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 100)
    private String rua;

    @Column
    private int numero;

    @Column(length = 45)
    private String bairro;
    
    @Column(length = 45)
    private String complemento;
    
    @Column(length = 45)
    private String cidade;
    
    @Column(length = 45)
    private String estado;
    
    @Column(length = 15)
    private String cep;

    @Column(nullable = false)
    private LocalDateTime registro;
    
}
