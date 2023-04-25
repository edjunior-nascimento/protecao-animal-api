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
    @Operation(summary = "Cadastrar uma nova regra", description = "Cadastrar uma nova regra" )
    public ResponseEntity<AcoesModel> saveAcoes(@RequestBody @Valid AcoesDto acoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(acoesService.save(acoesDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Page<AcoesModel>> getListAcoes(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(acoesService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir regra por ID", description = "Exibir uma determinada regra pelo seu ID")
    public ResponseEntity<AcoesModel> getAcoes(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(acoesService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar regra", description = "Atualiza uma determinada regra passando o seu ID e as configurações que deseja")
    public ResponseEntity<AcoesModel> updateAcoes(@PathVariable("id") UUID id, @RequestBody @Valid AcoesDto acoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(acoesService.save(acoesDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma regra", description = "Deleta uma determinada regra passando o seu ID")
    public ResponseEntity<String> deleteAcoes(@PathVariable("id") UUID id){
        acoesService.delete(acoesService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
