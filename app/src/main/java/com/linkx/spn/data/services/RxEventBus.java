package com.linkx.spn.data.services;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;


/**
 * Created by ulyx.yang on 2016/11/6.
 */
public class RxEventBus {
    private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());
    private final static RxEventBus instance = new RxEventBus();

    private RxEventBus() {}

    public static <T> Subscription onEvent(Class<T> clazz, Action1<T> handler) {
        return instance.subject
                .ofType(clazz)
                .subscribe(handler);
    }

    public static <T> Subscription onEventMainThread(Class<T> clazz, Action1<T> handler) {
        return instance.subject
                .ofType(clazz)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(handler);
    }

    public static void post(Object event) {
        instance.subject.onNext(event);
    }
}
