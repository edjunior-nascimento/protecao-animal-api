package com.api.protecaoanimal.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/anuncios")
public class AnunciosControler {

    final AnunciosService anunciosService;

    public AnunciosControler(AnunciosService anunciosService){
        this.anunciosService = anunciosService;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova parceiro", description = "Criar uma nova parceiro" )
    public ResponseEntity<Object> saveanuncios(@RequestBody @Valid AnunciosDto anunciosDto){
        var anunciosModel = new AnunciosModel();
        BeanUtils.copyProperties(anunciosDto, anunciosModel);
        anunciosModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciosService.save(anunciosModel));
    }

    @GetMapping
    @Operation(summary = "Listar todos os anuncios", description = "Listar todos os anuncios")
    public ResponseEntity<Object> getListanuncios(){
        return ResponseEntity.status(HttpStatus.OK).body(anunciosService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir parceiro por ID", description = "Exibir uma determinada parceiro pelo seu ID")
    public ResponseEntity<Object> getanuncios(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(anunciosService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar parceiro", description = "Atualiza uma determinada parceiro passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updateanuncios(@PathVariable("id") UUID id, @RequestBody @Valid AnunciosDto anunciosDto){
        Optional<AnunciosModel> anunciosModelOptional = anunciosService.findById(id);
        if (!anunciosModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        var anunciosModel = new AnunciosModel();
        BeanUtils.copyProperties(anunciosDto, anunciosModel);
        anunciosModel.setId(anunciosModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(anunciosService.save(anunciosModel));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma parceiro", description = "Deleta uma determinada parceiro passando o seu ID")
    public ResponseEntity<Object> deleteanuncios(@PathVariable("id") UUID id){
        Optional<AnunciosModel> anunciosModelOptional = anunciosService.findById(id);
        if (!anunciosModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        anunciosService.delete(anunciosModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
