package com.Indus.Simulator.Util;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.Indus.Simulator.Pojo.Request;
import com.Indus.Simulator.Pojo.Response;
import com.upi.merchanttoolkit.security.UPISecurity;


@Component

public class UpiUtils {

	private static final Logger log = LogManager.getLogger(UpiUtils.class);

	public static String encryptRequest(String request,String merchantKey) {

		String encryptedReques = null;
		try {
			encryptedReques = new UPISecurity().encrypt(request, merchantKey);
		} catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			encryptedReques = null;
		}
		log.info("merchantKey >>" + merchantKey + " EncryptRequestData >>" + encryptedReques);
		return encryptedReques;
	}

	
	
	public static String decryptResponse(String encryptedData,String merchantKey) {
		String decryptResponse = null;
		try {
			JSONObject resObj = new JSONObject(encryptedData);
			log.info("----------------> " + resObj.getString("requestMsg"));
			decryptResponse = new UPISecurity().decrypt(resObj.getString("requestMsg"), merchantKey);
		} catch (Exception e) {
			log.info("356 ----> " + ExceptionUtils.getStackTrace(e));
			decryptResponse = null;
		}
		log.info("merchantKey >> " + merchantKey + " decryptResponse >>" + decryptResponse);		
		return decryptResponse;
	}


	
	
	
public static JSONObject preparedBankApIRequest(Response res) {

	Request req  = new Request();
	JSONObject objList = new JSONObject();
	try {
		HashMap<String,String> requestlist=new HashMap<>();
				
		requestlist.put("status",res.getStatus());
		requestlist.put("statusDesc", res.getStatusDesc());
		requestlist.put("pspRefNo", "234323342324");
		requestlist.put("upiTransRefNo",req.getUpiTransRefNo());
		requestlist.put("npciTransId","121EDFS67");
		requestlist.put("custRefNo","32189391");
		requestlist.put("amount",req.getAmount());
		requestlist.put("txnAuthDate","07-08-2023");
		requestlist.put("addInfo","msg");
		requestlist.put("payerVPA","8114674993@ybl");
		requestlist.put("payeeVPA","soumya@ybl");
		requestlist.put("pgMerchantId",req.getPgMerchantId());

		objList.put("apiResp", requestlist);
		
	} catch (JSONException e) {
		log.info("Exception >>>>>>>>>> " + ExceptionUtils.getStackTrace(e));
		objList = null;
	}
	return objList;
}

}
