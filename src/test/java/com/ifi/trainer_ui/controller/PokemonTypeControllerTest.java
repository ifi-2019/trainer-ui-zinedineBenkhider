package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.bo.PokemonType;
import com.ifi.trainer_ui.service.PokemonTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeControllerTest {
    @Test
    void controllerShouldBeAnnotated(){
        assertNotNull(PokemonTypeController.class.getAnnotation(Controller.class));
    }

    @Test
    void pokemons_shouldReturnAModelAndView() {
        PokemonTypeService pokemonTypeService = mock(PokemonTypeService.class);
        List<PokemonType> pokemonTypeList=new ArrayList<>();
        pokemonTypeList.add(new PokemonType());
        pokemonTypeList.add(new PokemonType());
        when(pokemonTypeService.listPokemonsTypes()).thenReturn(pokemonTypeList);

        PokemonTypeController pokemonTypeController = new PokemonTypeController();
        pokemonTypeController.setPokemonTypeService(pokemonTypeService);
        ModelAndView modelAndView = pokemonTypeController.pokedex();

        assertEquals("pokedex", modelAndView.getViewName());
        List<PokemonType> pokemons = (List<PokemonType>)modelAndView.getModel().get("pokemonTypes");
        assertEquals(2, pokemons.size());
        verify(pokemonTypeService).listPokemonsTypes();
    }

    @Test
    void pokemons_shouldBeAnnotated() throws NoSuchMethodException {
        Method pokemonsMethod = PokemonTypeController.class.getDeclaredMethod("pokedex");
        GetMapping getMapping = pokemonsMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/pokedex"}, getMapping.value());
    }


}