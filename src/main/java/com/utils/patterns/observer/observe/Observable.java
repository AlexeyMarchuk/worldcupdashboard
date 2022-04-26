package com.utils.patterns.observer.observe;


import com.utils.patterns.observer.subscriber.Subscriber;

public interface Observable<T> {

    void addSubscriber(Subscriber<T> subscriber);

    void removeSubscriber(Subscriber<T> subscriber);

    void update(T object);

    void finish(T object);
}
