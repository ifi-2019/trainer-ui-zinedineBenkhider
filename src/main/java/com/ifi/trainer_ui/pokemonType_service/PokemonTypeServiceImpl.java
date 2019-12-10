package com.ifi.trainer_ui.pokemonType_service;

import com.ifi.trainer_ui.bo.PokemonType;
import com.ifi.trainer_ui.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;

    private String pokemonServiceUrl;

    public List<PokemonType> listPokemonsTypes() {
        PokemonType[] listPokemonType= restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/", PokemonType[].class);
        List<PokemonType> res=new ArrayList<>();
        for(int i=0;i<listPokemonType.length;i++){
            res.add(listPokemonType[i]);
        }
        return res;
    }


    private PokemonType getPokemonWithId(String id) {
        PokemonType pokemonType= restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/"+id, PokemonType.class);
        return pokemonType;
    }

    @Override
    public void setListPokemonsTypesForTrainer(Trainer trainer) {
        List<PokemonType> res=new ArrayList<>();
        String typePokemon;
        String levelPokemonType;
        for(int i=0;i<trainer.getTeam().size();i++){

            typePokemon=trainer.getTeam().get(i).getPokemonType();
            levelPokemonType = trainer.getTeam().get(i).getLevel();

            PokemonType pokemonType =getPokemonWithId(typePokemon);//recuperer le pokemon de l'api pokemon type
            pokemonType.setLevel(levelPokemonType);//lui atribuer le niveau du pokemon qui se trouve dans trainer
            res.add(pokemonType);
        }
        trainer.setTeam(res);
    }


    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }
    @Value("${pokemonType.service.url}")
        void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
            this.pokemonServiceUrl=pokemonServiceUrl;
        }



}
