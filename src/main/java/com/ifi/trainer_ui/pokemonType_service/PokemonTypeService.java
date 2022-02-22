package com.ifi.trainer_ui.pokemonType_service;

import com.ifi.trainer_ui.bo.PokemonType;
import com.ifi.trainer_ui.bo.Trainer;
import java.util.List;

public interface PokemonTypeService {
    List<PokemonType> listPokemonsTypes();
    void setListPokemonsTypesForTrainer(Trainer trainer);
}
