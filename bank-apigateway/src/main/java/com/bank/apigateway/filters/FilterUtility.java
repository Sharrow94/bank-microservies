package com.bank.apigateway.filters;

import static java.util.Optional.of;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
	
	public static final String CORRELATION_ID="bank-correlation-id";
	
	public String getCorrelationId(HttpHeaders httpHeaders) {
		return of(CORRELATION_ID)
				.map(httpHeaders::get)
				.map(List::stream)
				.map(Stream::findFirst)
				.map(Optional::get).orElse(null);
	}
	
	public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name,String value) {
		return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
	}

	public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
		return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
	}

}
