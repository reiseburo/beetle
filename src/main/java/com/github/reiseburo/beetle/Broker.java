package com.github.reiseburo.beetle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

/**
 * Simple POJO containing informtaion about a Kafka broker
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown=true)
public class Broker {
    private String host;
    private String brokerId;
    private int port;
    private int jmxPort;

    @JsonProperty
    public String getHost() {
        return this.host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty(value="jmx_port")
    public int getJmxPort() {
        return jmxPort;
    }

    @JsonProperty
    public String getBrokerId() {
        return brokerId;
    }

    @JsonProperty
    public void setJmxPort(int jmxPort) {
        this.jmxPort = jmxPort;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Empty constructor for jackson-databind
     */
    public Broker() {
    }

    private Broker(Builder builder) {
        this.brokerId = builder.brokerId;
        this.host = builder.host;
        this.port = builder.port;
        this.jmxPort = builder.jmxPort;
    }

    public Broker inferIdFromPath(String znodePath) {
        String[] parts = znodePath.split("/");
        brokerId = parts[parts.length - 1];
        return this;
    }

    /**
     * @return Debugging representation of the Broker
     */
    public String toString() {
        return String.format("<Broker:%d (%s %s:%d [jmx:%d])>",
                hashCode(), brokerId, host, port, jmxPort);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Convert the JSON that Kafka stores in Zookeeper into a Broker representation
     *
     * @param jsonBuffer
     * @return
     * @throws IOException
     */
    public static Broker fromJSON(String jsonBuffer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonBuffer, Broker.class);
    }

    /**
     * Builder
     */
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
}
