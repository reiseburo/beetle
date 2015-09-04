package com.github.reiseburo.beetle.internal

import org.apache.curator.framework.CuratorFramework
import spock.lang.*

import rx.Observable
import rx.observers.TestSubscriber

/**
 */
class BrokersMonitorSpec extends Specification {
    CuratorFramework curator
    TestSubscriber subscriber

    def setup() {
        curator = Mock(CuratorFramework)
        subscriber = new TestSubscriber()
    }

    def "observe(curator) gives me an Observable"() {
        expect:
        BrokersMonitor.observe(curator) instanceof Observable<BrokersMonitor>
    }

    @Ignore('in progress')
    def "observe(curator) gives the subscriber a ZKBrokerSet"() {
        given:
        ZKBrokerSet brokers
        Observable o = BrokersMonitor.observe(curator)

        when:
        o.subscribe { ZKBrokerSet set -> brokers = set }

        then:
        brokers instanceof ZKBrokerSet
    }

    def "observe(curator) with a null will call the subscribers onError"() {
        given:
        Observable o = BrokersMonitor.observe(null)

        when:
        o.subscribe(subscriber)

        then:
        subscriber.assertError(NullPointerException.class)
    }
}
