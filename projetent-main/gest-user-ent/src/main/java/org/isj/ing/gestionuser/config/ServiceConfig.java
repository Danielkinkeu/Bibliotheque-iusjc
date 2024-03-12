package org.isj.ing.gestionuser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Service
@Configuration
public class ServiceConfig {
    @Value("${signing.key}")
    private String jwtSigningKey = "";

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        return filter;
    }

}