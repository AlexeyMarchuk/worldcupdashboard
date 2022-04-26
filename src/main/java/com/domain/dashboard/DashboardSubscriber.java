package com.domain.dashboard;

import com.domain.dto.Game;
import com.utils.patterns.observer.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DashboardSubscriber implements Subscriber<Game> {

    private final Map<UUID, Game> games = new ConcurrentHashMap<>() {
        @Override
        public String toString() {
            return remissScoreFirst();
        }

        private String remissScoreFirst() {
            List<Game> col = new CopyOnWriteArrayList<>(this.values());
            List<Game> sameScore = new ArrayList<>();

            col.forEach(a -> {
                if (a.getAwayTeam().getScore() == a.getHomeTeam().getScore()) {
                    sameScore.add(a);
                    col.remove(a);
                }
            });

            sameScore.sort(Game::compareTo);

            List<Game> result = Stream.of(sameScore, col)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            return result.toString();
        }
    };

    public DashboardSubscriber(GameManager gameManager) {
        gameManager.addSubscriber(this);
    }

    public void update(Game game) {
        if (games.get(game.getId()) == null) {
            games.put(game.getId(), game);
        } else {
            games.replace(game.getId(), game);
        }
    }


    @Override
    public void finish(Game game) {
        games.remove(game.getId());
    }

    public Map<UUID, Game> getGames() {
        return games;
    }


}
