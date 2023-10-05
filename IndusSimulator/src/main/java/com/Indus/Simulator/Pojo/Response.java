package com.Indus.Simulator.Pojo;

import lombok.Data;

@Data
public class Response {

	private String status;
	private String statusDesc;
	
	
	private String pspRefNo;
	private String upiTransRefNo;
	private String npciTransId;
	private String custRefNo;
	private String amount;
	private String txnAuthDate;
	private Object addInfo;
	private String payerVPA;
	private String payeeVPA;
	private String pgMerchantId;
}
