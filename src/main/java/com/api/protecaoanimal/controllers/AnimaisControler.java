package com.api.protecaoanimal.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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

import com.api.protecaoanimal.dtos.AnimaisDto;
import com.api.protecaoanimal.dtos.FotosDto;
import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.FotosModel;
import com.api.protecaoanimal.services.AnimaisService;
import com.api.protecaoanimal.services.FotosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Animais")
@RequestMapping("/animais")
public class AnimaisControler {

    final AnimaisService animaisService;

    public AnimaisControler(AnimaisService animaisService){
        this.animaisService = animaisService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo animal", description = "Cadastrar um novo animal" )
    public ResponseEntity<Object> saveanimais(@RequestBody @Valid AnimaisDto animaisDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(animaisService.save(animaisDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os animais", description = "Listar todos os animais")
    public ResponseEntity<Object> getListanimais(){
        return ResponseEntity.status(HttpStatus.OK).body(animaisService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir animal por ID", description = "Exibir uma determinado animal pelo seu ID")
    public ResponseEntity<Object> getanimais(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(animaisService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de animal", description = "Atualizar dados de animal")
    public ResponseEntity<Object> updateanimais(@PathVariable("id") UUID id, @RequestBody @Valid AnimaisDto animaisDto){
        Optional<AnimaisModel> animaisModelOptional = animaisService.findById(id);
        if (!animaisModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(animaisService.save(animaisDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma animal pelo ID", description = "Deleta uma determinado animal passando o seu ID")
    public ResponseEntity<Object> deleteanimais(@PathVariable("id") UUID id){
        Optional<AnimaisModel> animaisModelOptional = animaisService.findById(id);
        if (!animaisModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        animaisService.delete(animaisModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
