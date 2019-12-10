package com.ifi.trainer_ui.trainers_service;


import com.ifi.trainer_ui.bo.Trainer;

import java.util.List;


public interface TrainerService {
    List<Trainer> getAllTrainers(String currentTrainerName);
    Trainer getTrainer(String name);
    void updatePassword(String name, String newPassword);
}


