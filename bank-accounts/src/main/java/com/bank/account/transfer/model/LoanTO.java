package com.bank.account.transfer.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoanTO {

	private int loanNumber;

	private int customerId;

	private Date startDt;

	private String loanType;

	private int totalLoan;

	private int amountPaid;

	private int outstandingAmount;

	private String createDt;

}
