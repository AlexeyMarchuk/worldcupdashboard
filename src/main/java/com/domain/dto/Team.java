package com.domain.dto;

import com.exceptions.IncorrectScoreValueException;

public class Team implements Cloneable {

    private final Country name;
    private int score = 0;

    public Team(Country country) {
        name = country;
    }

    public Country getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IncorrectScoreValueException();
        }
        this.score = score;
    }

    public void addPoint() {
        score++;
    }

    @Override
    public Team clone() {
        Team clone = new Team(this.getName());
        clone.setScore(this.getScore());

        return clone;
    }


}
