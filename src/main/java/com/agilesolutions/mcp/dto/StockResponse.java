package com.agilesolutions.mcp.dto;

import lombok.Builder;

@Builder
public record StockResponse(Float price) {
}
