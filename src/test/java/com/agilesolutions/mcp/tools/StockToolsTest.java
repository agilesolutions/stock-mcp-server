package com.agilesolutions.mcp.tools;

import com.agilesolutions.mcp.config.ApplicationProperties;
import com.agilesolutions.mcp.config.RestConfig;
import com.agilesolutions.mcp.dto.DailyShareQuote;
import com.agilesolutions.mcp.dto.StockResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringJUnitConfig(classes = {RestConfig.class, ApplicationProperties.class, StockTools.class}, initializers = {ConfigDataApplicationContextInitializer.class})
@TestPropertySource(properties = { "spring.config.location=classpath:application.yaml" })
@EnableConfigurationProperties(value = ApplicationProperties.class)
class StockToolsTest {


    @Autowired
    StockTools stockTools;


    @Test
    public void givenAvailable_whenRetrieving_thenReturnStocks() throws JsonProcessingException {

        StockResponse response = stockTools.getLatestStockPrices("AAPL");

        assertAll("verify result",
                () -> assertTrue(response.price() > 0)
        );


    }

    @Test
    public void givenAvailable_whenRetrieving_thenReturnStockss() throws JsonProcessingException {

        List<DailyShareQuote> dailyShareQuotes = stockTools.getHistoricalStockPrices(5,"AAPL");

        assertAll("verify result",
                () -> assertTrue(dailyShareQuotes.size() > 0)
        );


    }

}