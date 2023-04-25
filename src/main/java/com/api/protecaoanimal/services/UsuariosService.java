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

import com.api.protecaoanimal.dtos.UsuariosDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.models.UsuariosModel;
import com.api.protecaoanimal.repositories.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuariosService {
    
    final UsuariosRepository usuariosRepository;

    final RegrasService regrasService;

    public UsuariosService(UsuariosRepository usuariosRepository, RegrasService regrasService) {
        this.usuariosRepository = usuariosRepository;
        this.regrasService = regrasService;
    }

    @Transactional
    public UsuariosModel save(UsuariosDto usuariosDto) {
        var usuariosModel = new UsuariosModel();
        BeanUtils.copyProperties(usuariosDto, usuariosModel);
        usuariosModel.setSenha(new BCryptPasswordEncoder().encode(usuariosModel.getLogin()));
        usuariosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return usuariosRepository.save(usuariosModel);
    }

    public UsuariosModel update(UUID id, UsuariosDto usuariosDto) {
        var usuariosModel = new UsuariosModel();
        BeanUtils.copyProperties(usuariosDto, usuariosModel);
        usuariosModel.setId(id);
        usuariosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return usuariosRepository.save(usuariosModel);
    }

    public Page<UsuariosModel> findAll(Pageable pageable) {
        return usuariosRepository.findAll(pageable);
    }

    public UsuariosModel findById(UUID id) {
        Optional<UsuariosModel> usuariosModel = usuariosRepository.findById(id);
        return usuariosModel.orElseThrow(() -> new ItemNotFoundException("UUID de usuário não existe"));
    }

    public void delete(UsuariosModel usuariosModel) {
        findById(usuariosModel.getId());
        usuariosRepository.delete(usuariosModel);
    }

    public UsuariosModel insertRegras(UUID idUsuario, List<UUID> idRegras) {
        var usuariosModel = findById(idUsuario);
        var listaRegrasModel = usuariosModel.getRegras();
        idRegras.forEach(id->listaRegrasModel.add(regrasService.findById(id)));
        usuariosModel.setRegras(listaRegrasModel);
        return usuariosRepository.save(usuariosModel);
    }

    public void deleteRegras(UUID idUsuario, List<UUID> idRegras){
        var usuariosModel = findById(idUsuario);
        var listaRegrasModel = usuariosModel.getRegras();
        idRegras.forEach(id->listaRegrasModel.remove(regrasService.findById(id)));
        usuariosModel.setRegras(listaRegrasModel);
        usuariosRepository.save(usuariosModel);
    }

}
