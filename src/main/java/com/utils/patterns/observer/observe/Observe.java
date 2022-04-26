package com.utils.patterns.observer.observe;


import com.utils.patterns.observer.subscriber.Subscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public  class Observe<T> implements Observable<T> {

    public List<Subscriber<T>> subscribers = new CopyOnWriteArrayList<>();

    @Override
    public void addSubscriber(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber<T> subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void update(T t) {
        subscribers.forEach(a -> a.update(t));
    }

    @Override
    public void finish(T t) {
        subscribers.forEach(a -> a.finish(t));
    }

}
