package com.api.protecaoanimal.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.protecaoanimal.dtos.AdocoesDto;
import com.api.protecaoanimal.models.AdocoesModel;
import com.api.protecaoanimal.services.AdocoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "adocões")
@RequestMapping("/adocoes")
public class AdocoesControler {

    final AdocoesService adocoesService;

    public AdocoesControler(AdocoesService adocoesService){
        this.adocoesService = adocoesService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova adoção", description = "Cadastrar uma nova adoção" )
    public ResponseEntity<Object> saveadocoes(@RequestBody @Valid AdocoesDto adocoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(adocoesService.save(adocoesDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Object> getListadocoes(){
        return ResponseEntity.status(HttpStatus.OK).body(adocoesService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir adoção por ID", description = "Exibir uma determinada adoção pelo seu ID")
    public ResponseEntity<Object> getadocoes(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(adocoesService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar adoção", description = "Atualiza uma determinada adoção passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updateadocoes(@PathVariable("id") UUID id, @RequestBody @Valid AdocoesDto adocoesDto){
        Optional<AdocoesModel> adocoesModelOptional = adocoesService.findById(id);
        if (!adocoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(adocoesService.save(adocoesDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma adoção", description = "Deleta uma determinada adoção passando o seu ID")
    public ResponseEntity<Object> deleteadocoes(@PathVariable("id") UUID id){
        Optional<AdocoesModel> adocoesModelOptional = adocoesService.findById(id);
        if (!adocoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        adocoesService.delete(adocoesModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
