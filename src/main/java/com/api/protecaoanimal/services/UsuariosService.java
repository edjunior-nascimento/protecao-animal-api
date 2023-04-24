package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.RegrasDto;
import com.api.protecaoanimal.dtos.UsuariosDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.models.UsuariosModel;
import com.api.protecaoanimal.repositories.RegrasRepository;
import com.api.protecaoanimal.repositories.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuariosService {
    
    final UsuariosRepository usuariosRepository;

    final RegrasRepository regrasRepository;

    public UsuariosService(UsuariosRepository usuariosRepository, RegrasRepository regrasRepository) {
        this.usuariosRepository = usuariosRepository;
        this.regrasRepository = regrasRepository;
    }

    @Transactional
    public UsuariosModel criarUsuario(UsuariosDto usuariosDto) {
        var usuariosModel = new UsuariosModel();
        BeanUtils.copyProperties(usuariosDto, usuariosModel);
        usuariosModel.setSenha(new BCryptPasswordEncoder().encode(usuariosModel.getLogin()));
        usuariosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return usuariosRepository.save(usuariosModel);
    }

    public Page<UsuariosModel> buscarTodosUsuarios(Pageable pageable) {
        return usuariosRepository.findAll(pageable);
    }

    public UsuariosModel buscarUsuario(UUID id) {
        Optional<UsuariosModel> usuariosModel = usuariosRepository.findById(id);
        return usuariosModel.orElseThrow(() -> new ItemNotFoundException("UUID de usuário não existe"));
    }

    public void deletarUsuario(UsuariosModel usuariosModel) {
        buscarUsuario(usuariosModel.getId());
        usuariosRepository.delete(usuariosModel);
    }


    @Transactional
    public RegrasModel criarRegra(RegrasDto regrasDto) {
        var regrasModel = new RegrasModel();
        BeanUtils.copyProperties(regrasDto, regrasModel);
        regrasModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return regrasRepository.save(regrasModel);
    }

    public Page<RegrasModel> buscarTodasRegras(Pageable pageable) {
        return regrasRepository.findAll(pageable);
    }

    public RegrasModel buscarRegra(UUID id) {
        Optional<RegrasModel> regrasModel = regrasRepository.findById(id);
        return regrasModel.orElseThrow(() -> new ItemNotFoundException("UUID de regra não existe"));
    }

    public void deletarRegra(RegrasModel regrasModel) {
        
        buscarUsuario(regrasModel.getId());
        regrasRepository.delete(regrasModel);
    }


    public void adicionarRegrasDeUsuario(UUID idUsuario, List<UUID> idRegras) {
        var listaRegrasModel = new ArrayList<RegrasModel>();
        var usuariosModel = buscarUsuario(idUsuario);
        idRegras.forEach(id->listaRegrasModel.add(buscarRegra(id)));
        usuariosModel.setRegras(listaRegrasModel);
        usuariosRepository.save(usuariosModel);
    }

    public void deletarRegrasDeUsuario(UUID idUsuario, List<UUID> idRegras){
        var usuariosModel = buscarUsuario(idUsuario);
        var listaRegrasModel = usuariosModel.getRegras();
        idRegras.forEach(id->listaRegrasModel.remove(buscarRegra(id)));
        usuariosModel.setRegras(listaRegrasModel);
        usuariosRepository.save(usuariosModel);
    }

}
