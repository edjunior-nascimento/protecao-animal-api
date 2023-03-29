package com.api.protecaoanimal.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "adocoes")
public class AdocoesModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idAnimal")
    private AnimaisModel animais;

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

    @Column()
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
