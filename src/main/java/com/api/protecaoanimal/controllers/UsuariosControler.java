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

import com.api.protecaoanimal.dtos.RegrasDto;
import com.api.protecaoanimal.dtos.UsuariosDto;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.models.UsuariosModel;
import com.api.protecaoanimal.services.UsuariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.criarUsuario(usuariosDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Page<UsuariosModel>> buscarTodosUsuarios(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.buscarTodosUsuarios(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir usuário por ID", description = "Exibir uma determinada usuário pelo seu ID")
    public ResponseEntity<UsuariosModel> buscarUsuario(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.buscarUsuario(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza uma determinada usuário passando o seu ID e as configurações que deseja")
    public ResponseEntity<UsuariosModel> atualizarUsuario(@PathVariable("id") UUID id, @RequestBody @Valid UsuariosDto usuariosDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.criarUsuario(usuariosDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma usuário", description = "Deleta uma determinada usuário passando o seu ID")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") UUID id){
        usuariosService.deletarUsuario(usuariosService.buscarUsuario(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }


    
    @PostMapping("/regras")
    @Operation(summary = "Cadastrar uma nova regra", description = "Cadastrar uma nova regra" )
    public ResponseEntity<RegrasModel> criarRegra(@RequestBody @Valid RegrasDto regrasDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.criarRegra(regrasDto));
    }

    @GetMapping("/regras")
    @Operation(summary = "Listar todas as regras", description = "Listar todas as ações")
    public ResponseEntity<Page<RegrasModel>> buscarTodasRegras(@PageableDefault(page = 0, size = 10, sort = "registro", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.buscarTodasRegras(pageable));
    }

    @GetMapping("/regras/{id}")
    @Operation(summary = "Exibir regra por ID", description = "Exibir uma determinada regra pelo seu ID")
    public ResponseEntity<RegrasModel> buscarRegra(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(usuariosService.buscarRegra(id));
    }

    @PutMapping("/regras/{id}")
    @Operation(summary = "Atualizar regra", description = "Atualiza uma determinada regra passando o seu ID e as configurações que deseja")
    public ResponseEntity<RegrasModel> atualizarRegra(@PathVariable("id") UUID id, @RequestBody @Valid RegrasDto regrasDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.criarRegra(regrasDto));
    }

    @DeleteMapping("/regras/{id}")
    @Operation(summary = "Deleta uma regra", description = "Deleta uma determinada regra passando o seu ID")
    public ResponseEntity<String> deletarRegra(@PathVariable("id") UUID id){
        usuariosService.deletarRegra(usuariosService.buscarRegra(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

    @PostMapping("/add-regras")
    @Operation(summary = "Adicionar regras a usuario", description = "Adicionar regras a usuario" )
    public ResponseEntity<String> adicionarRegrasDeUsuario(@RequestBody UUID id_usuario, @RequestBody List<UUID> id_regras){
        usuariosService.adicionarRegrasDeUsuario(id_usuario, id_regras);
        return ResponseEntity.status(HttpStatus.OK).body("Regra vinculada a usuario");
    }

    @DeleteMapping("/del-regras")
    @Operation(summary = "Deletar regras de usuario", description = "Deletar regras de usuario" )
    public ResponseEntity<String> deletarRegrasDeUsuario(@RequestBody UUID id_usuario, @RequestBody List<UUID> id_regras){
        usuariosService.deletarRegrasDeUsuario(id_usuario, id_regras);
        return ResponseEntity.status(HttpStatus.OK).body("Regra(s) removida(s) de usuario");
    }


}
