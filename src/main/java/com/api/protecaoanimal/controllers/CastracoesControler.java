package com.api.protecaoanimal.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.protecaoanimal.dtos.CastracoesDto;
import com.api.protecaoanimal.models.CastracoesModel;
import com.api.protecaoanimal.services.CastracoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Castração")
@RequestMapping("/castracao")
public class CastracoesControler {

    final CastracoesService castracoesService;

    public CastracoesControler(CastracoesService castracoesService){
        this.castracoesService = castracoesService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova ação", description = "Cadastrar uma nova ação" )
    public ResponseEntity<CastracoesModel> savecastracoes(@RequestBody @Valid CastracoesDto castracoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(castracoesService.save(castracoesDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Page<CastracoesModel>> getListcastracoes(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(castracoesService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir ação por ID", description = "Exibir uma determinada Ação pelo seu ID")
    public ResponseEntity<CastracoesModel> getcastracoes(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(castracoesService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    @Operation(summary = "Atualizar ação", description = "Atualiza uma determinada Ação passando o seu ID e as configurações que deseja")
    public ResponseEntity<CastracoesModel> updatecastracoes(@PathVariable("id") UUID id, @RequestBody @Valid CastracoesDto castracoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(castracoesService.update(id, castracoesDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    @Operation(summary = "Deleta uma ação", description = "Deleta uma determinada Ação passando o seu ID")
    public ResponseEntity<Void> deletecastracoes(@PathVariable("id") UUID id){
        castracoesService.delete(castracoesService.findById(id));
        return ResponseEntity.noContent().build();
    }

}
