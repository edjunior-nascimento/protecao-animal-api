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

import com.api.protecaoanimal.dtos.AcoesDto;
import com.api.protecaoanimal.models.AcoesModel;
import com.api.protecaoanimal.services.AcoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Ações")
@RequestMapping("/acoes")
public class AcoesControler {

    final AcoesService acoesService;

    public AcoesControler(AcoesService acoesService){
        this.acoesService = acoesService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova ação", description = "Cadastrar uma nova ação" )
    public ResponseEntity<Object> saveAcoes(@RequestBody @Valid AcoesDto acoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(acoesService.save(acoesDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Object> getListAcoes(){
        return ResponseEntity.status(HttpStatus.OK).body(acoesService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir ação por ID", description = "Exibir uma determinada Ação pelo seu ID")
    public ResponseEntity<Object> getAcoes(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(acoesService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ação", description = "Atualiza uma determinada Ação passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updateAcoes(@PathVariable("id") UUID id, @RequestBody @Valid AcoesDto acoesDto){
        Optional<AcoesModel> acoesModelOptional = acoesService.findById(id);
        if (!acoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(acoesService.save(acoesDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma ação", description = "Deleta uma determinada Ação passando o seu ID")
    public ResponseEntity<Object> deleteAcoes(@PathVariable("id") UUID id){
        Optional<AcoesModel> acoesModelOptional = acoesService.findById(id);
        if (!acoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        acoesService.delete(acoesModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
