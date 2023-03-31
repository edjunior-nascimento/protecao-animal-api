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

import com.api.protecaoanimal.dtos.SituacoesDto;
import com.api.protecaoanimal.models.SituacoesModel;
import com.api.protecaoanimal.services.SituacoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Situações")
@RequestMapping("/situacoes")
public class SituacoesControler {

    final SituacoesService situacoesService;

    public SituacoesControler(SituacoesService situacoesService){
        this.situacoesService = situacoesService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova situação para animais", description = "Cadastrar uma nova situação para animais" )
    public ResponseEntity<Object> savesituacoes(@RequestBody @Valid SituacoesDto situacoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(situacoesService.save(situacoesDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as situações para animais", description = "Listar todas as situacões para animais")
    public ResponseEntity<Object> getListsituacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(situacoesService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir situações por ID", description = "Exibir uma determinado situações pelo seu ID")
    public ResponseEntity<Object> getsituacoes(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(situacoesService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar situações", description = "Atualiza um determinado situação passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updatesituacoes(@PathVariable("id") UUID id, @RequestBody @Valid SituacoesDto situacoesDto){
        Optional<SituacoesModel> situacoesModelOptional = situacoesService.findById(id);
        if (!situacoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(situacoesService.save(situacoesDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um situação", description = "Deleta uma determinado situação passando o seu ID")
    public ResponseEntity<Object> deletesituacoes(@PathVariable("id") UUID id){
        Optional<SituacoesModel> situacoesModelOptional = situacoesService.findById(id);
        if (!situacoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        situacoesService.delete(situacoesModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
