package com.Indus.Simulator.Pojo;


import lombok.Data;

@Data
public class Request {

	private String pgMerchantId;
	private String pspRefNo;
	private String amount;
	private String transactionNote;
	private String addInfo9;
	private String addInfo10;
	private String upiTransRefNo;
	private String virtualAddress;
	private String expiryTime;
	private boolean isMerchant;
	private boolean showMerchant;
	private boolean defVPAStatus;
	
//	private String amount;
//    private PayerType payerType;
//    private String upiTransRefNo;
//    private String transactionNote;
//    private String expiryTime;
//    private RequestInfo requestInfo;
//    private AdditionalInfo addInfo;
	
}
