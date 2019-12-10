package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.bo.Trainer;
import com.ifi.trainer_ui.pokemonType_service.PokemonTypeService;
import com.ifi.trainer_ui.trainers_service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class TrainerController {

    TrainerService trainerService;
    PokemonTypeService pokemonTypeService;

    @GetMapping(value = "/trainers")
    public ModelAndView trainers(Principal principal){
        ModelAndView modelAndView=new ModelAndView("trainers");
        List<Trainer> trainersList= trainerService.getAllTrainers(principal.getName());
        for (int i=0;i<trainersList.size();i++){
            pokemonTypeService.setListPokemonsTypesForTrainer(trainersList.get(i)); //attribuer à chaque trainer la liste de pokemons avec toutes les informations(API pokemon).
        }

        modelAndView.addObject("allTrainers",trainersList);
        return modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService=trainerService;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService=pokemonTypeService;
    }

}
