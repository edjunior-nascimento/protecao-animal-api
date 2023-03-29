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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "animais")
public class AnimaisModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(length = 50)
    private String nome;

    @Column(length = 1)
    private int especie;

    @Column(length = 1)
    private int sexo;
    
    @Column(length = 2)
    private int idade;
    
    @Column(length = 1)
    private int porte;
    
    @Column(length = 50)
    private String raca;
    
    @Column()
    private List<Integer> temperamento;
    
    @Column()
    private List<Integer> situacao;
    
    @Column()
    private String historia;

    @Column(nullable = false)
    private LocalDateTime registro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animais")
    private List<FotosModel> fotos;
    
}
