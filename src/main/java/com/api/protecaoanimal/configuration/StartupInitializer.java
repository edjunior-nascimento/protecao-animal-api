package com.api.protecaoanimal.configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api.protecaoanimal.enuns.RegrasEnum;
import com.api.protecaoanimal.enuns.SituacoesEnum;
import com.api.protecaoanimal.enuns.TemperamentosEnum;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.models.SituacoesModel;
import com.api.protecaoanimal.models.TemperamentosModel;
import com.api.protecaoanimal.repositories.RegrasRepository;
import com.api.protecaoanimal.repositories.SituacoesRepository;
import com.api.protecaoanimal.repositories.TemperamentosRepository;

@Component
public class StartupInitializer implements CommandLineRunner {

    private RegrasRepository regrasRepository;
    private SituacoesRepository situacoesRepository;
    private TemperamentosRepository temperamentosRepository;

    public StartupInitializer(RegrasRepository regrasRepository,
                                SituacoesRepository situacoesRepository,
                                TemperamentosRepository temperamentosRepository ){

        this.regrasRepository = regrasRepository;
        this.situacoesRepository = situacoesRepository;
        this.temperamentosRepository = temperamentosRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        //Inserindo dados basicos na base de dados
        inserirRegrasEnum();
        inserirSituacoesEnum();
        inserirTemperamentosEnum();

    }


    private void inserirRegrasEnum(){
        for (RegrasEnum regrasEnum : RegrasEnum.values()) {
            if(!regrasRepository.existsByNome(regrasEnum.name())){
                RegrasModel regrasModel = new RegrasModel();
                regrasModel.setNome(regrasEnum.name());
                regrasModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
                regrasRepository.save(regrasModel);
            }
        }
    }

    private void inserirSituacoesEnum(){
        for (SituacoesEnum situacoesEnum : SituacoesEnum.values()) {
            if(!situacoesRepository.existsByNome(situacoesEnum.name())){
                SituacoesModel situacoesModel = new SituacoesModel();
                situacoesModel.setNome(situacoesEnum.getNome());
                situacoesModel.setDescricao(situacoesEnum.getDescription());
                situacoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
                situacoesRepository.save(situacoesModel);
            }
        }
    }

    private void inserirTemperamentosEnum(){
        for (TemperamentosEnum temperamentosEnum : TemperamentosEnum.values()) {
            if(!temperamentosRepository.existsByNome(temperamentosEnum.name())){
                TemperamentosModel temperamentosModel = new TemperamentosModel();
                temperamentosModel.setNome(temperamentosEnum.getNome());
                temperamentosModel.setDescricao(temperamentosEnum.getDescription());
                temperamentosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
                temperamentosRepository.save(temperamentosModel);
            }
        }
    }

    
}
