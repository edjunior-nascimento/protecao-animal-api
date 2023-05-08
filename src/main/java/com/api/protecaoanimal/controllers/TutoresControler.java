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
    public ResponseEntity<TutoresModel> savetutores(@RequestBody @Valid TutoresDto tutoresDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tutoresService.save(tutoresDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os tutores", description = "Listar todos os tutores")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<Page<TutoresModel>> getListtutores(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(tutoresService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir tutor por ID", description = "Exibir uma determinado tutor pelo seu ID")
    public ResponseEntity<TutoresModel> gettutores(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(tutoresService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    @Operation(summary = "Atualizar tutor", description = "Atualiza um determinado tutor passando o seu ID e as configurações que deseja")
    public ResponseEntity<TutoresModel> updatetutores(@PathVariable("id") UUID id, @RequestBody @Valid TutoresDto tutoresDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tutoresService.update(id, tutoresDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    @Operation(summary = "Deleta uma tutor", description = "Deleta uma determinada tutor passando o seu ID")
    public ResponseEntity<Void> deletetutores(@PathVariable("id") UUID id){
        tutoresService.delete(tutoresService.findById(id));
        return ResponseEntity.noContent().build();
    }

}
