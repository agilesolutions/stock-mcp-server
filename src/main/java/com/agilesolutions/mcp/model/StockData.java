package com.agilesolutions.mcp.model;

import lombok.Data;

import java.util.List;

@Data
public class StockData {

    private List<DailyStockData> values;

}