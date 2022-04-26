package com.utils.patterns.observer.subscriber;

import com.domain.dto.Game;

import java.util.Map;
import java.util.UUID;

public interface Subscriber<T> {

    void update(T object);
    void finish(T object);
    Map<UUID, Game> getGames();
}
