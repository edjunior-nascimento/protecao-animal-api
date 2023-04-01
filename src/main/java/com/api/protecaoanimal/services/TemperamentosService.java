package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.api.protecaoanimal.dtos.TemperamentosDto;
import com.api.protecaoanimal.models.TemperamentosModel;
import com.api.protecaoanimal.repositories.TemperamentosRepository;

import jakarta.transaction.Transactional;

@Service
public class TemperamentosService {
    
    final TemperamentosRepository temperamentosRepository;

    public TemperamentosService(TemperamentosRepository temperamentosRepository) {
        this.temperamentosRepository = temperamentosRepository;
    }

    @Transactional
    public TemperamentosModel save(TemperamentosDto temperamentosDto) {
        var temperamentosModel = new TemperamentosModel();
        BeanUtils.copyProperties(temperamentosDto, temperamentosModel);
        temperamentosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return temperamentosRepository.save(temperamentosModel);
    }

    public List<TemperamentosModel> findAll() {
        return temperamentosRepository.findAll();
    }

    public TemperamentosModel findById(UUID id) {
        return temperamentosRepository.findById(id).get();
    }

    public void delete(TemperamentosModel temperamentosModel) {
        temperamentosRepository.delete(temperamentosModel);
    }

}
