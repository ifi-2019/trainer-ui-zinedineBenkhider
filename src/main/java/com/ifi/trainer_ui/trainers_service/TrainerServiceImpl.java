package com.ifi.trainer_ui.trainers_service;

import com.ifi.trainer_ui.bo.PokemonType;
import com.ifi.trainer_ui.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    private RestTemplate restTemplate;

    private String trainerServiceUrl;

    public List<Trainer> getAllTrainers() {
        Trainer[] listTrainers =restTemplate.getForObject(trainerServiceUrl+"/trainers/", Trainer[].class);
        List<Trainer> res=new ArrayList<>();
        for(int i=0;i<listTrainers.length;i++){
            res.add(listTrainers[i]);
        }
        return res;
    }

    @Override
    public Trainer getTrainer(String name) {
        Trainer trainer =restTemplate.getForObject(trainerServiceUrl+"/trainers/"+name, Trainer.class);
        return trainer;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainers.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.trainerServiceUrl=pokemonServiceUrl;
    }

}
