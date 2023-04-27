package com.api.protecaoanimal.configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api.protecaoanimal.enuns.RegrasEnum;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.repositories.RegrasRepository;

@Component
public class RegrasInitializer implements CommandLineRunner {

    private RegrasRepository regrasRepository;

    public RegrasInitializer(RegrasRepository regrasRepository){
        this.regrasRepository = regrasRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (RegrasEnum regrasEnum : RegrasEnum.values()) {
            if(!regrasRepository.existsByNome(regrasEnum.name())){
                RegrasModel regrasModel = new RegrasModel();
                regrasModel.setNome(regrasEnum.name());
                regrasModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
                regrasRepository.save(regrasModel);
            }
        }
    }
    
}
