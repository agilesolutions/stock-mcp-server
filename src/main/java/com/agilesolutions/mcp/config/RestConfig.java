package com.agilesolutions.mcp.config;

import com.agilesolutions.mcp.rest.StockClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestConfig {

    @Bean("stockClient")
    public StockClient stockClient(ApplicationProperties applicationProperties) {
        RestClient restClient = RestClient.builder().baseUrl(applicationProperties.getUrl()).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(StockClient.class);
    }

}
