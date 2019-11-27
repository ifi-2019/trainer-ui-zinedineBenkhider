package com.ifi.trainer_ui.service;

import com.ifi.trainer_ui.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
            System.out.println("list PokemonType size " +listPokemonType.length);
            List<PokemonType> res=new ArrayList<>();
            for(int i=0;i<listPokemonType.length;i++){
                res.add(listPokemonType[i]);
            }
            return res;
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
