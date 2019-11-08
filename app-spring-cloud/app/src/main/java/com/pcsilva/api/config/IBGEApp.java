package com.pcsilva.api.config;

import com.pcsilva.api.service.IBGEServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class IBGEApp {

    @Autowired
    private IBGEServiceDelegate ibgeServiceDelegate;

    @Bean
    @Scope( value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public IBGEScope applicationScopedBean() {

        return new IBGEScope();
    }

}
