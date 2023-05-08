package com.api.protecaoanimal.controllers;

import java.util.List;
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

import com.api.protecaoanimal.dtos.UsuariosDto;
import com.api.protecaoanimal.models.UsuariosModel;
import com.api.protecaoanimal.services.UsuariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Usuários")
@RequestMapping("/usuarios")
public class UsuariosControler {

    final UsuariosService usuariosService;

    public UsuariosControler(UsuariosService usuariosService){
        this.usuariosService = usuariosService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova usuário", description = "Cadastrar uma nova usuário" )
    public ResponseEntity<UsuariosModel> criarUsuario(@RequestBody @Valid UsuariosDto usuariosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.save(usuariosDto));
    }

    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    @Operation(summary = "Listar todos os usuarios", description = "Listar todos os usuarios")
    public ResponseEntity<Page<UsuariosModel>> buscarTodosUsuarios(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir usuário por ID", description = "Exibir uma determinada usuário pelo seu ID")
    public ResponseEntity<UsuariosModel> buscarUsuario(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza uma determinada usuário passando o seu ID e as configurações que deseja")
    public ResponseEntity<UsuariosModel> atualizarUsuario(@PathVariable("id") UUID id, @RequestBody @Valid UsuariosDto usuariosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.update(id, usuariosDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma usuário", description = "Deleta uma determinada usuário passando o seu ID")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") UUID id){
        usuariosService.delete(usuariosService.findById(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/regras")
    @Operation(summary = "Adicionar regras a usuario", description = "Adicionar regras a usuario" )
    public ResponseEntity<UsuariosModel> insertRegras(@PathVariable("id") UUID idUsuario, @RequestBody List<UUID> idRegras){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.insertRegras(idUsuario, idRegras));
    }

    @DeleteMapping("/{id}/regras")
    @Operation(summary = "Deletar regras de usuario", description = "Deletar regras de usuario" )
    public ResponseEntity<Void> deleteRegras(@PathVariable("id") UUID idUsuario, @RequestBody List<UUID> idRegras){
        usuariosService.deleteRegras(idUsuario, idRegras);
        return ResponseEntity.noContent().build();
    }


}
