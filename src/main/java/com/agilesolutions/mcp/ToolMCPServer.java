package com.agilesolutions.mcp;

import com.agilesolutions.mcp.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class ToolMCPServer {

    public static void main(String[] args) {
        SpringApplication.run(ToolMCPServer.class, args);
    }

}