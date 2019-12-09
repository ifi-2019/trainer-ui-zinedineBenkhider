package com.ifi.trainer_ui.bo;

import java.util.List;

public class Trainer {

    private String name;
    private String password;
    private List<PokemonType> team;

    public Trainer() {
    }

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonType> getTeam() {
        return team;
    }

    public void setTeam(List<PokemonType> team) {
        this.team = team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
