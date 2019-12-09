package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.bo.Trainer;
import com.ifi.trainer_ui.trainers_service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class TrainerController {

    TrainerService trainerService;
    @GetMapping(value = "/trainers")
    public ModelAndView trainers(){
        ModelAndView modelAndView=new ModelAndView("trainers");
        List<Trainer> trainersList= trainerService.getAllTrainers();
        modelAndView.addObject("allTrainers",trainersList);
        return modelAndView;
    }



    @Autowired
    public void setPokemonTypeService(TrainerService trainerService) {
        this.trainerService=trainerService;
    }

}
