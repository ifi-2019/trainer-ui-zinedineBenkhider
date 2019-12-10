package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.bo.PokemonType;
import com.ifi.trainer_ui.bo.Trainer;
import com.ifi.trainer_ui.pokemonType_service.PokemonTypeService;
import com.ifi.trainer_ui.trainers_service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {
    PokemonTypeService pokemonTypeService;
    TrainerService trainerService;
    @GetMapping(value = "/profile")
    public ModelAndView profile(Principal principal){
        Trainer trainer=trainerService.getTrainer(principal.getName());
        pokemonTypeService.setListPokemonsTypesForTrainer(trainer);
        List<PokemonType> pokemonTypeList=trainer.getTeam();
        ModelAndView modelAndView=new ModelAndView("profile");
        modelAndView.addObject("pokemons",pokemonTypeList);
        return modelAndView;
    }
    @GetMapping(value = "/UpdatePsw")
    public ModelAndView updatePassword(){
        ModelAndView modelAndView=new ModelAndView("updatePsw");
        return modelAndView;
    }

    @PostMapping(value = "/UpdatePassword")
    public String updatePasswordPost(Principal principal,@PathVariable String oldPassword,@PathVariable String newPassword,@PathVariable String confirmPassword){
        trainerService.updatePassword(principal.getName(),newPassword);
        return "redirect:/profile";
    }
    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService=pokemonTypeService;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService=trainerService;
    }


}
