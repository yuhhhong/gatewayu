package com.yu.gatewayu.handler;

import com.yu.gatewayu.interfaces.DiscoveryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigDiscoveryHandler implements DiscoveryHandler {
    @Autowired
    private Environment environment;

    @Override
    public String getUrlByName(String serverName) {
        String config = "gatewayu.server." + serverName;
        return environment.getProperty(config);
    }
}
