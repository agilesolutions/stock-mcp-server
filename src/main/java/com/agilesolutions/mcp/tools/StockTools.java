package com.agilesolutions.mcp.tools;

import com.agilesolutions.mcp.config.ApplicationProperties;
import com.agilesolutions.mcp.dto.DailyShareQuote;
import com.agilesolutions.mcp.dto.StockResponse;
import com.agilesolutions.mcp.model.DailyStockData;
import com.agilesolutions.mcp.model.StockData;
import com.agilesolutions.mcp.rest.StockClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockTools {

    @Qualifier("stockClient")
    private final StockClient stockClient;

    private final ApplicationProperties applicationProperties;

    private static final String MINUTE_INTERVAL = "1min";
    private static final String DAY_INTERVAL = "1day";

    @Tool(description = "get actual stock prices")
    public StockResponse getLatestStockPrices(@ToolParam(description = "Name of company") String company) {
        log.info("Get stock prices for: {}", company);
        StockData data = stockClient.getLatestStockPrices(company, MINUTE_INTERVAL, 1, applicationProperties.getKey());
        DailyStockData latestData = data.getValues().get(0);
        log.info("Get stock prices ({}) -> {}", company, latestData.getClose());
        return new StockResponse(Float.parseFloat(latestData.getClose()));
    }

    @Tool(description = "Historical daily stock prices")
    public List<DailyShareQuote> getHistoricalStockPrices(@ToolParam(description = "Search period in days") int days,
                                                          @ToolParam(description = "Name of company") String company) {
        log.info("Get historical stock prices: {} for {} days", company, days);
        StockData data = stockClient.getHistoricalStockPrices(company, DAY_INTERVAL, days, applicationProperties.getKey());
        return data.getValues().stream()
                .map(d -> new DailyShareQuote(company, Float.parseFloat(d.getClose()), d.getDatetime()))
                .toList();
    }


}
