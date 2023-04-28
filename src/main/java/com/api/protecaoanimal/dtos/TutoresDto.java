package com.api.protecaoanimal.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutoresDto {

    @NotBlank
    @Size(max = 45 ,message = "Campo descricao não pode ser maior que 45 caracteres")
    private String nome;

    @Size(max = 45 ,message = "Campo descricao não pode ser maior que 45 caracteres")
    private String telefone;

    @Email
    private String email;

    @CPF
    private String cpf;

    @Size(max = 20 ,message = "Campo descricao não pode ser maior que 20 caracteres")
    private String rg;

    @Size(max = 100 ,message = "Campo descricao não pode ser maior que 100 caracteres")
    private String rua;

    @Max(value = 10 ,message = "Campo descricao não pode ser maior que 10 caracteres")
    private int numero;

     @Size(max = 45 ,message = "Campo descricao não pode ser maior que 45 caracteres")
    private String bairro;
    
     @Size(max = 45 ,message = "Campo descricao não pode ser maior que 45 caracteres")
    private String complemento;
    
     @Size(max = 45 ,message = "Campo descricao não pode ser maior que 45 caracteres")
    private String cidade;
    
     @Size(max = 45 ,message = "Campo descricao não pode ser maior que 45 caracteres")
    private String estado;
    
     @Size(max = 15 ,message = "Campo descricao não pode ser maior que 15 caracteres")
    private String cep;
    
}
