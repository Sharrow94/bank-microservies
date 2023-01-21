package com.bank.account.transfer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.account.transfer.model.CardTO;
import com.bank.account.transfer.model.CustomerTO;

@FeignClient(name="card")
public interface CardFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = "application/json")
	List<CardTO> getCardDetails(@RequestHeader("bank-correlation-id") String correlationId,@RequestBody CustomerTO customer);
}
