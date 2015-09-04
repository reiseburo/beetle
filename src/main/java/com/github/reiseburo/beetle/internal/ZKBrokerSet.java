package com.github.reiseburo.beetle.internal;

import com.github.reiseburo.beetle.Broker;
import org.apache.curator.framework.CuratorFramework;

import java.util.Set;

/**
 */
public class ZKBrokerSet {
    protected Set<Broker> brokers;
    protected CuratorFramework activeCurator;

    public Set<Broker> getBrokers() {
        return brokers;
    }

    public CuratorFramework getCurator() {
        return activeCurator;
    }

}
