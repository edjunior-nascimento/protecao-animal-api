package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.AnimaisDto;
import com.api.protecaoanimal.dtos.FotosDto;
import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.FotosModel;
import com.api.protecaoanimal.repositories.AnimaisRepository;

import jakarta.transaction.Transactional;

@Service
public class AnimaisService {

    final AnimaisRepository animaisRepository;
    final FotosService fotosService;

    public AnimaisService(AnimaisRepository animaisRepository, FotosService fotosService) {
        this.animaisRepository = animaisRepository;
        this.fotosService = fotosService;
    }

    @Transactional
    public AnimaisModel save(AnimaisDto animaisDto) {
        var animaisModel = new AnimaisModel();
        BeanUtils.copyProperties(animaisDto, animaisModel);
        animaisModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));

        var savedAnimaisModel = animaisRepository.save(animaisModel);

        var listFotos = new ArrayList<FotosModel>();

        for (FotosDto foto : animaisDto.getFotos()) {
            var fotosModel = new FotosModel();
            BeanUtils.copyProperties(foto, fotosModel);
            fotosModel.setAnimais(savedAnimaisModel);
            fotosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
            listFotos.add(fotosService.save(fotosModel));
        }
        savedAnimaisModel.setFotos(listFotos);

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
