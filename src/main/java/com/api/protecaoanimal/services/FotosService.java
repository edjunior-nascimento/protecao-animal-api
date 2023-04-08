package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.FotosDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.AnimaisModel;
import com.api.protecaoanimal.models.FotosModel;
import com.api.protecaoanimal.repositories.FotosRepository;

import jakarta.transaction.Transactional;

@Service
public class FotosService {

    final FotosRepository fotosRepository;

    public FotosService(FotosRepository fotosRepository) {
        this.fotosRepository = fotosRepository;
    }

    @Transactional
    public FotosModel save(FotosModel fotosModel) {
        return fotosRepository.save(fotosModel);
    }

    public List<FotosModel> save(AnimaisModel animaisModel, List<FotosDto> fotosDto){
        var listFotos = new ArrayList<FotosModel>();

        for (FotosDto foto : fotosDto) {
            var fotosModel = new FotosModel();
            BeanUtils.copyProperties(foto, fotosModel);
            fotosModel.setAnimais(animaisModel);
            fotosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
            listFotos.add(this.save(fotosModel));
        }
        return listFotos;
    }

    public List<FotosModel> findAll() {
        return fotosRepository.findAll();
    }

    public FotosModel findById(UUID id) {
        Optional<FotosModel> fotosModel = fotosRepository.findById(id);
        return fotosModel.orElseThrow(ItemNotFoundException::new);
    }

    public void delete(FotosModel fotosModel) {
        findById(fotosModel.getId());
        fotosRepository.delete(fotosModel);
    }
    
}
