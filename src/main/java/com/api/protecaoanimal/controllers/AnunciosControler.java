package com.api.protecaoanimal.controllers;

import java.util.UUID;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.api.protecaoanimal.dtos.AnunciosDto;
import com.api.protecaoanimal.models.AnunciosModel;
import com.api.protecaoanimal.services.AnunciosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Anúncios")
@RequestMapping("/anuncios")
public class AnunciosControler {

    final AnunciosService anunciosService;

    public AnunciosControler(AnunciosService anunciosService){
        this.anunciosService = anunciosService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo anuncio", description = "Cadastrar um novo anuncio" )
    public ResponseEntity<AnunciosModel> saveanuncios(@RequestBody @Valid AnunciosDto anunciosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciosService.save(anunciosDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os anuncios", description = "Listar todos os anuncios")
    public ResponseEntity<Page<AnunciosModel>> getListanuncios(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(anunciosService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir anuncio por ID", description = "Exibir uma determinado anuncio pelo seu ID")
    public ResponseEntity<AnunciosModel> getanuncios(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(anunciosService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar anuncio", description = "Atualiza um determinado anuncio passando o seu ID e as configurações que deseja")
    public ResponseEntity<AnunciosModel> updateanuncios(@PathVariable("id") UUID id, @RequestBody @Valid AnunciosDto anunciosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciosService.save(anunciosDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma anuncio", description = "Deleta uma determinada anuncio passando o seu ID")
    public ResponseEntity<String> deleteanuncios(@PathVariable("id") UUID id){
        anunciosService.delete(anunciosService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo. ");
    }

}
