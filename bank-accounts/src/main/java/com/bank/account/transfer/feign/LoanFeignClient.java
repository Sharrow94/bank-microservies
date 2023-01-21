package com.bank.account.transfer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.account.transfer.model.CustomerTO;
import com.bank.account.transfer.model.LoanTO;

@FeignClient(name="loan")
public interface LoanFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "myLoans", consumes = "application/json")
	List<LoanTO> getLoanDetails(@RequestHeader("bank-correlation-id") String correlationId,@RequestBody CustomerTO customer);
}
