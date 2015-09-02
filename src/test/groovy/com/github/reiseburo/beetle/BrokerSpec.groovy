package com.github.reiseburo.beetle

import spock.lang.*

/**
 */
class BrokerSpec extends Specification {
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
}
