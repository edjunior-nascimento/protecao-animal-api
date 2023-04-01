package com.api.protecaoanimal.controllers;

import java.util.List;
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

import com.api.protecaoanimal.dtos.ParceirosDto;
import com.api.protecaoanimal.models.ParceirosModel;
import com.api.protecaoanimal.services.ParceirosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Parceiros")
@RequestMapping("/parceiros")
public class ParceirosControler {

    final ParceirosService parceirosService;

    public ParceirosControler(ParceirosService parceirosService){
        this.parceirosService = parceirosService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova parceiro", description = "Cadastrar uma nova parceiro" )
    public ResponseEntity<ParceirosModel> saveParceiros(@RequestBody @Valid ParceirosDto parceirosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(parceirosService.save(parceirosDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os parceiros", description = "Listar todos os parceiros")
    public ResponseEntity<List<ParceirosModel>> getListParceiros(){
        return ResponseEntity.status(HttpStatus.OK).body(parceirosService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir parceiro por ID", description = "Exibir uma determinada parceiro pelo seu ID")
    public ResponseEntity<ParceirosModel> getParceiros(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(parceirosService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar parceiro", description = "Atualiza uma determinada parceiro passando o seu ID e as configurações que deseja")
    public ResponseEntity<ParceirosModel> updateParceiros(@PathVariable("id") UUID id, @RequestBody @Valid ParceirosDto parceirosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(parceirosService.save(parceirosDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma parceiro", description = "Deleta uma determinada parceiro passando o seu ID")
    public ResponseEntity<String> deleteParceiros(@PathVariable("id") UUID id){
        parceirosService.delete(parceirosService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
