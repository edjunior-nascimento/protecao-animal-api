package com.api.protecaoanimal.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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
    private Date nascimento;
    
    @Column(length = 1)
    private int porte;
    
    @Column(length = 50)
    private String raca;
    
    @Column
    private String historia;

    @Column
    private String observacao;

    @Column(nullable = false)
    private LocalDateTime registro;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "animais")
    private List<TemperamentosModel> temperamentos;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "animais")
    private List<SituacoesModel> situacoes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animais")
    private List<FotosModel> fotos;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "animais")
    private CastracoesModel castracao;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "animais")
    private AdocoesModel adocao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idTutor")
    private TutoresModel tutores;
    
}
