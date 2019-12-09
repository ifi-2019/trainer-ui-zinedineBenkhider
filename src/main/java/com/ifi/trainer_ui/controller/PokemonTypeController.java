package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.bo.PokemonType;
import com.ifi.trainer_ui.pokemonType_service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PokemonTypeController {
    PokemonTypeService pokemonTypeService;
    @GetMapping(value = "/pokedex")
    public ModelAndView pokedex(){
        ModelAndView modelAndView=new ModelAndView("pokedex");
        List<PokemonType> pokemonTypeList= pokemonTypeService.listPokemonsTypes();
        modelAndView.addObject("pokemonTypes",pokemonTypeList);
        return modelAndView;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService=pokemonTypeService;
    }
}
