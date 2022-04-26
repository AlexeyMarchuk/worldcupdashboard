package com.domain.dashboard;

import com.domain.dto.Country;
import com.domain.dto.Game;
import com.domain.dto.Team;
import com.exceptions.InvalidTeamException;
import com.exceptions.TeamAlreadyPlayingException;
import com.utils.patterns.observer.observe.Observe;

import java.util.ArrayList;
import java.util.List;

public class GameManager extends Observe<Game> {

    private final List<Country> playingCountries = new ArrayList<>();

    public Game start(Team homeTeam, Team awayTeam) {
        if (isTeamsValid(homeTeam, awayTeam)) {
            addToActivePlayers(homeTeam.getName(), awayTeam.getName());
            Game game = new Game(homeTeam, awayTeam);
            update(game.clone());
            return game;
        }
        throw new InvalidTeamException();
    }

    @Override
    public void finish(Game game) {
        super.finish(game);
        playingCountries.remove(game.getHomeTeam().getName());
        playingCountries.remove(game.getAwayTeam().getName());
    }

    private boolean isTeamsValid(Team homeTeam, Team awayTeam) {
        if (homeTeam != null && awayTeam != null &&
                !homeTeam.getName().equals(awayTeam.getName()) &&
                teamsAreAvailable(homeTeam.getName(), awayTeam.getName())) {
            addToActivePlayers(homeTeam.getName(), awayTeam.getName());
            return true;
        }
        return false;
    }

    private boolean teamsAreAvailable(Country homeName, Country awayName) {
        if (!playingCountries.contains(homeName) && !playingCountries.contains(awayName)) {
            return true;
        }
        throw new TeamAlreadyPlayingException();
    }

    private void addToActivePlayers(Country homeName, Country awayName) {
        playingCountries.add(homeName);
        playingCountries.add(awayName);
    }

}
