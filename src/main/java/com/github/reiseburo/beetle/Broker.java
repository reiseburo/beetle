package com.github.reiseburo.beetle;

/**
 * Simple POJO containing informtaion about a Kafka broker
 */
public class Broker {
    private String host;

    private String brokerId;
    private int port;
    private int jmxPort;

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return port;
    }

    public int getJmxPort() {
        return jmxPort;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public static class Builder {
        private int port;
        private int jmxPort;
        private String host;
        private String brokerId;

        public Builder withHost(String hostName) {
            this.host = hostName;
            return this;
        }

        public Builder withPort(int port) {
            this.port = port;
            return this;
        }

        public Builder withJMXPort(int port) {
            this.jmxPort = port;
            return this;
        }

        public Builder withBrokerId(String id) {
            this.brokerId = id;
            return this;
        }

        public Broker build() {
            return new Broker(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Broker(Builder builder) {
        this.brokerId = builder.brokerId;
        this.host = builder.host;
        this.port = builder.port;
        this.jmxPort = builder.jmxPort;
    }

    public String toString() {
        return String.format("<Broker:%d (%s %s:%d [jmx:%d])>",
                hashCode(), brokerId, host, port, jmxPort);
    }
}
