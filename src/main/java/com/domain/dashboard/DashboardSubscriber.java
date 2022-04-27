package com.domain.dashboard;

import com.domain.dto.Game;
import com.utils.Utils;
import com.utils.patterns.observer.subscriber.Subscriber;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class DashboardSubscriber implements Subscriber<Game> {

    private final Map<UUID, Game> games = new ConcurrentHashMap<>();

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

    public List<Game> getSortedGames() {
        return Utils.getSortedGames(games);
    }


}
