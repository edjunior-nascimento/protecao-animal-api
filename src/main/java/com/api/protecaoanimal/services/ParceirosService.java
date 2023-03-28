package com.api.protecaoanimal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.protecaoanimal.models.ParceirosModel;
import com.api.protecaoanimal.repositories.ParceirosRepository;

import jakarta.transaction.Transactional;

@Service
public class ParceirosService {
    
    final ParceirosRepository parceirosRepository;

    public ParceirosService(ParceirosRepository parceirosRepository) {
        this.parceirosRepository = parceirosRepository;
    }

    @Transactional
    public ParceirosModel save(ParceirosModel parceirosModel) {
        return parceirosRepository.save(parceirosModel);
    }

    public List<ParceirosModel> findAll() {
        return parceirosRepository.findAll();
    }

    public Optional<ParceirosModel> findById(UUID id) {
        return parceirosRepository.findById(id);
    }

    public void delete(ParceirosModel parceirosModel) {
        parceirosRepository.delete(parceirosModel);
    }

}
