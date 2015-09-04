package com.github.reiseburo.beetle.internal;

import org.apache.curator.framework.CuratorFramework;
import rx.Observable;
import rx.Subscriber;

/**
 * BrokersMonitor exists largely create an Observable<BrokersMonitor> which
 * will properly watch Zookeeper for changes in the /brokers subtree
 */
public class BrokersMonitor {

    /**
     * Internal constructor for created a BrokersMonitor object.
     *
     * Use BrokersMonitor.observe() to subscribe to instances instead
     *
     * @param curator
     */
    protected BrokersMonitor(CuratorFramework curator) {
        if (curator == null) {
            throw new NullPointerException("`curator` cannot be null");
        }
    }

    /**
     * Create an {@code Observable<T>} for watching the Zookeeper connection
     * associated with the given {@code CuratorFramework} object.
     *
     * Subscribers will receive onError if the BrokersMonitor could not be constructed
     *
     * Subscribers will never receive onCompleted
     *
     * @param curator
     * @return
     */
    public static Observable<ZKBrokerSet> observe(final CuratorFramework curator) {
        return Observable.create(new Observable.OnSubscribe<ZKBrokerSet>() {
            @Override
            public void call(Subscriber<? super ZKBrokerSet> subscriber) {
                try {
                    BrokersMonitor monitor = new BrokersMonitor(curator);
                    subscriber.onNext(null);
                }
                catch (NullPointerException npe) {
                    subscriber.onError(npe);
                }
            }
        });
    }
}
