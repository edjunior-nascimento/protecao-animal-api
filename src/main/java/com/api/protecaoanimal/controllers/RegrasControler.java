package com.api.protecaoanimal.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
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

import com.api.protecaoanimal.dtos.RegrasDto;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.services.RegrasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Regras")
@RequestMapping("/regras")
public class RegrasControler {

    final RegrasService regrasService;

    public RegrasControler(RegrasService regrasService){
        this.regrasService = regrasService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova ação", description = "Cadastrar uma nova ação" )
    public ResponseEntity<RegrasModel> saveregras(@RequestBody @Valid RegrasDto regrasDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(regrasService.save(regrasDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as regras", description = "Listar todas as regras")
    public ResponseEntity<Page<RegrasModel>> getListregras(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(regrasService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir ação por ID", description = "Exibir uma determinada Ação pelo seu ID")
    public ResponseEntity<RegrasModel> getregras(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(regrasService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ação", description = "Atualiza uma determinada Ação passando o seu ID e as configurregras que deseja")
    public ResponseEntity<RegrasModel> updateregras(@PathVariable("id") UUID id, @RequestBody @Valid RegrasDto regrasDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(regrasService.update(id, regrasDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma ação", description = "Deleta uma determinada Ação passando o seu ID")
    public ResponseEntity<Void> deleteregras(@PathVariable("id") UUID id){
        regrasService.delete(regrasService.findById(id));
        return ResponseEntity.noContent().build();
    }

}
