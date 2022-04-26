package com.domain.dto;

import java.util.UUID;

public class Game implements Cloneable {
    private UUID id;
    private Team homeTeam;
    private Team awayTeam;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        id = UUID.randomUUID();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public Game clone() {
        Game clone = new Game(this.awayTeam.clone(), this.homeTeam.clone());
        clone.id = this.id;
        return clone;
    }
}
