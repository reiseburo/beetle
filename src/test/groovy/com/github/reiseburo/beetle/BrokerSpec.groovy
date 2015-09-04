package com.github.reiseburo.beetle

import spock.lang.*

/**
 */
class BrokerSpec extends Specification {
    def "fromJSON() with proper JSON should return the expected Broker"() {
        given:
        final String json = '{"jmx_port":9999,"timestamp":"1428168559585","host":"kafka.example.org","version":1,"port":6667}'
        Broker broker

        when:
        broker = Broker.fromJSON(json)

        then:
        broker instanceof Broker
        broker.host == 'kafka.example.org'
        broker.port == 6667
        broker.jmxPort == 9999
    }

    def "inferIdFromPath() should work properly"() {
        given:
        Broker broker = Broker.builder().build()

        when:
        broker.inferIdFromPath('/brokers/ids/123')

        then:
        broker.brokerId == '123'
    }
}

class BrokerBuilderSpec extends Specification {
    def "builder() pattern should create a Broker"() {
        expect:
        Broker.builder().build() instanceof Broker
    }

    def "withHost()"() {
        given:
        final String hostName = 'localhost'
        Broker broker

        when:
        broker = Broker.builder().withHost(hostName).build()

        then:
        broker.host == hostName
    }

    def "withPort()"() {
        given:
        final int port = 1234
        Broker broker

        when:
        broker = Broker.builder().withPort(port).build()

        then:
        broker.port == port
    }

    def "withJMXPort()"() {
        given:
        final int port = 1234
        Broker broker

        when:
        broker = Broker.builder().withJMXPort(port).build()

        then:
        broker.jmxPort == port
    }

    def "withBrokerId()"() {
        given:
        final String brokerId = 'roflcopter'
        Broker broker

        when:
        broker = Broker.builder().withBrokerId(brokerId).build()

        then:
        broker.brokerId == brokerId
    }
}
