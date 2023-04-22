package com.api.protecaoanimal.models;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "regras")
public class RegrasModel implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuarios_regras", 
        joinColumns = { @JoinColumn(name = "idRegras") }, 
        inverseJoinColumns = { @JoinColumn(name = "idUsuarios") }
    )
    @JsonIgnore
    private List<UsuariosModel> usuarios;

    @Override
    public String getAuthority() {
        return this.nome.toString();
    }
    
}
