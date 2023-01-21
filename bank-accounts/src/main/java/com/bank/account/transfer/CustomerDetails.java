package com.bank.account.transfer;

import java.util.List;

import com.bank.account.model.Account;
import com.bank.account.transfer.model.CardTO;
import com.bank.account.transfer.model.LoanTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDetails {
	
	private Account account;
	private List<LoanTO> loans;
	private List<CardTO> cards;
}
