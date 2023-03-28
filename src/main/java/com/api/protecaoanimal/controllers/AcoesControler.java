package com.api.protecaoanimal.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

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

import com.api.protecaoanimal.dtos.AcoesDto;
import com.api.protecaoanimal.models.AcoesModel;
import com.api.protecaoanimal.models.AnimalModel;
import com.api.protecaoanimal.services.AcoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/acoes")
public class AcoesControler {

    final AcoesService acoesService;

    public AcoesControler(AcoesService acoesService){
        this.acoesService = acoesService;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova ação", description = "Criar uma nova ação" )
    public ResponseEntity<Object> saveAcoes(@RequestBody @Valid AcoesDto acoesDto){
        //if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
        //    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        //}
        //if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
        //    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        //}
        //if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
        //    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
        //}
        var acoesModel = new AcoesModel();
        BeanUtils.copyProperties(acoesDto, acoesModel);
        acoesModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(acoesService.save(acoesModel));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Object> getListAcoes(){
        return null;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir ação por ID", description = "Exibir uma determinada Ação pelo seu ID")
    public ResponseEntity<Object> getAcoes(@PathVariable String id){
        return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ação", description = "Atualiza uma determinada Ação passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updateAcoes(@PathVariable("id") String id, @RequestBody @Valid AcoesDto acoesDto){
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma ação", description = "Deleta uma determinada Ação passando o seu ID")
    public ResponseEntity<Object> deleteAcoes(@PathVariable("id") String id){
        return null;
    }

}
