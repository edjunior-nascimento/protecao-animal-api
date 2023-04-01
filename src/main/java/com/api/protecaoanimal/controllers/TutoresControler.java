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

import com.api.protecaoanimal.dtos.TutoresDto;
import com.api.protecaoanimal.models.TutoresModel;
import com.api.protecaoanimal.services.TutoresService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Tutores")
@RequestMapping("/tutores")
public class TutoresControler {

    final TutoresService tutoresService;

    public TutoresControler(TutoresService tutoresService){
        this.tutoresService = tutoresService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo Tutor", description = "Cadastrar um novo tutor" )
    public ResponseEntity<Object> savetutores(@RequestBody @Valid TutoresDto tutoresDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tutoresService.save(tutoresDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os tutores", description = "Listar todos os tutores")
    public ResponseEntity<Object> getListtutores(){
        return ResponseEntity.status(HttpStatus.OK).body(tutoresService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir tutor por ID", description = "Exibir uma determinado tutor pelo seu ID")
    public ResponseEntity<Object> gettutores(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(tutoresService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tutor", description = "Atualiza um determinado tutor passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updatetutores(@PathVariable("id") UUID id, @RequestBody @Valid TutoresDto tutoresDto){
        Optional<TutoresModel> tutoresModelOptional = tutoresService.findById(id);
        if (!tutoresModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tutoresService.save(tutoresDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma tutor", description = "Deleta uma determinada tutor passando o seu ID")
    public ResponseEntity<Object> deletetutores(@PathVariable("id") UUID id){
        Optional<TutoresModel> tutoresModelOptional = tutoresService.findById(id);
        if (!tutoresModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        tutoresService.delete(tutoresModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
