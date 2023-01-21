package com.bank.apigateway.filters;


import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class TraceFilterGateway implements GlobalFilter{
	
	private static final Logger LOG=LoggerFactory.getLogger(TraceFilterGateway.class);
	
	private FilterUtility filterUtility;
	
	public TraceFilterGateway(FilterUtility filterUtility) {
		this.filterUtility=filterUtility;
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders requestHeaders=exchange.getRequest().getHeaders();
		if (isCorrelationIdPresent(requestHeaders)) {
			LOG.debug("BankApp-correlation-id generated in trace filter: {}.",filterUtility.getCorrelationId(requestHeaders));
		} else {
			String correlationId=generateCorrelationId();
			exchange=filterUtility.setCorrelationId(exchange,correlationId);
			LOG.debug("BankApp-correlation-id generated in trace filter: {}.",correlationId);
		}
		return chain.filter(exchange);
	}
	
	private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
		return  Objects.nonNull(filterUtility.getCorrelationId(requestHeaders));
	}
	
	private String generateCorrelationId() {
		return UUID.randomUUID().toString();
	}

}
