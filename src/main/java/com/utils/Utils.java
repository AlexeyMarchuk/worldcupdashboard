package com.utils;

import com.domain.dto.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<Game> getSortedGames(Map<UUID, Game> map) {
        List<Game> allGames = new CopyOnWriteArrayList<>(map.values());
        List<Game> pairedGamesScore = new ArrayList<>();

        allGames.forEach(a -> {
            if (a.getAwayTeam().getScore() == a.getHomeTeam().getScore()) {
                pairedGamesScore.add(a);
                allGames.remove(a);
            }
        });

        pairedGamesScore.sort(Game::compareTo);

        return Stream.of(pairedGamesScore, allGames)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
