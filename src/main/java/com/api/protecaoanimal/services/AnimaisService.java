package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.AnimaisDto;
import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.SituacoesModel;
import com.api.protecaoanimal.models.TemperamentosModel;
import com.api.protecaoanimal.repositories.AnimaisRepository;

import jakarta.transaction.Transactional;

@Service
public class AnimaisService {

    final AnimaisRepository animaisRepository;
    final FotosService fotosService;
    final TemperamentosService temperamentosService;
    final SituacoesService situacoesService;

    public AnimaisService(
        AnimaisRepository animaisRepository, 
        FotosService fotosService, 
        TemperamentosService temperamentosService,
        SituacoesService situacoesService) {
        this.animaisRepository = animaisRepository;
        this.fotosService = fotosService;
        this.temperamentosService = temperamentosService;
        this.situacoesService = situacoesService;
    }

    @Transactional
    public AnimaisModel save(AnimaisDto animaisDto) {

        var animaisModel = new AnimaisModel();
        BeanUtils.copyProperties(animaisDto, animaisModel);
        animaisModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));

        animaisModel.setTemperamentos(animaisDto.getTemperamento().stream().map(v -> {
                 TemperamentosModel temperamentosModel = temperamentosService.findById(v).get();
                 temperamentosModel.getAnimais().add(animaisModel);
                 return temperamentosModel;
             }).collect(Collectors.toList()));

        animaisModel.setSituacoes(animaisDto.getSituacao().stream().map(v -> {
                SituacoesModel situacoesModel = situacoesService.findById(v).get();
                situacoesModel.getAnimais().add(animaisModel);
                return situacoesModel;
            }).collect(Collectors.toList()));

        var savedAnimaisModel = animaisRepository.save(animaisModel);

        var savedFotosModal = fotosService.save(savedAnimaisModel, animaisDto.getFotos());
        
        savedAnimaisModel.setFotos(savedFotosModal);

        return savedAnimaisModel;
    }

    public List<AnimaisModel> findAll() {
        return animaisRepository.findAll();
    }

    public Optional<AnimaisModel> findById(UUID id) {
        return animaisRepository.findById(id);
    }

    public void delete(AnimaisModel animaisModel) {
        animaisRepository.delete(animaisModel);
    }
    
}
