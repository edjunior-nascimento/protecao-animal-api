package com.api.protecaoanimal.controllers;

import java.util.List;
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

import com.api.protecaoanimal.dtos.AnimaisDto;
import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.services.AnimaisService;

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
    public ResponseEntity<AnimaisModel> saveanimais(@RequestBody @Valid AnimaisDto animaisDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(animaisService.save(animaisDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os animais", description = "Listar todos os animais")
    public ResponseEntity<Page<AnimaisModel>> getListanimais(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(animaisService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir animal por ID", description = "Exibir uma determinado animal pelo seu ID")
    public ResponseEntity<AnimaisModel> getanimais(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(animaisService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de animal", description = "Atualizar dados de animal")
    public ResponseEntity<AnimaisModel> updateanimais(@PathVariable("id") UUID id, @RequestBody @Valid AnimaisDto animaisDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(animaisService.update(id, animaisDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma animal pelo ID", description = "Deleta uma determinado animal passando o seu ID")
    public ResponseEntity<String> deleteanimais(@PathVariable("id") UUID id){
        animaisService.delete(animaisService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }


    @PostMapping("/{id}/situacoes")
    @Operation(summary = "Adicionar situação a animal", description = "Adicionar situação a animal" )
    public ResponseEntity<String> adicionarSituacoesDeAnimal(@PathVariable("id") UUID id_animal, @RequestBody List<UUID> id_situacoes){
        animaisService.adicionarSituacoesDeAnimal(id_animal, id_situacoes);
        return ResponseEntity.status(HttpStatus.OK).body("Situação(es) vinculada(s) a animal");
    }

    @DeleteMapping("/{id}/situacoes")
    @Operation(summary = "Deletar situacoes de animal", description = "Deletar situacoes de animal" )
    public ResponseEntity<String> deletarSituacoesDeAnimal(@PathVariable("id") UUID id_animal, @RequestBody List<UUID> id_situacoes){
        animaisService.deletarSituacoesDeAnimal(id_animal, id_situacoes);
        return ResponseEntity.status(HttpStatus.OK).body("Situação(es) removida(s) de animal");
    }

    @PostMapping("/{id}/temperamentos")
    @Operation(summary = "Adicionar temperamento a animal", description = "Adicionar temperamento a animal" )
    public ResponseEntity<String> adicionarTemperamentosDeAnimal(@PathVariable("id") UUID id_animal, @RequestBody List<UUID> id_temperamentos){
        animaisService.adicionarTemperamentosDeAnimal(id_animal, id_temperamentos);
        return ResponseEntity.status(HttpStatus.OK).body("Temperamento(s) vinculada(s) a animal");
    }

    @DeleteMapping("/{id}/temperamentos")
    @Operation(summary = "Deletar temperamentos de animal", description = "Deletar temperamentos de animal" )
    public ResponseEntity<String> deletarTemperamentosDeAnimal(@PathVariable("id") UUID id_animal, @RequestBody List<UUID> id_temperamentos){
        animaisService.deletarTemperamentosDeAnimal(id_animal, id_temperamentos);
        return ResponseEntity.status(HttpStatus.OK).body("Temperamento(s) removida(s) de animal");
    }


}
