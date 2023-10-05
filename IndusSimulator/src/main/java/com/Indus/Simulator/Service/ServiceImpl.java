package com.Indus.Simulator.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.Indus.Simulator.Pojo.Request;
import com.Indus.Simulator.Pojo.Response;
import com.Indus.Simulator.Util.UpiUtils;
import com.google.gson.Gson;


@Service

public class ServiceImpl {

	private static final Logger log = LogManager.getLogger(ServiceImpl.class);


	public String Welcome() {
		return "Simulator Is Active";
	}
	
	
	public HttpEntity<String> vpa(String encryptedData) {
		
		Response res = new Response();
//		String decryptedResponse = UpiUtils.decryptResponse(encryptedData, "*********");
		String decryptedResponse = UpiUtils.decryptResponse(encryptedData, "********");
		log.info("decrypted data ------------------->" + decryptedResponse);


		Gson gson = new Gson();
		Request req = gson.fromJson(decryptedResponse, Request.class);

		log.info("request after decryption ----------------> " + req);

		if(req.getAmount().matches("45"))
		{
			res.setStatus("S");
			res.setStatusDesc("Success Payment");
		}
		else if(req.getAmount().matches("10"))
		{	
			res.setStatus("P");
			res.setStatusDesc("Pending Payment");
		}
		else
		{
			res.setStatus("2");
			res.setStatusDesc("Failed Payment");
		}
		JSONObject objList = UpiUtils.preparedBankApIRequest(res);
//		String encryptedRequest = UpiUtils.encryptRequest(objList.toString(), "*******");
		String encryptedRequest = UpiUtils.encryptRequest(objList.toString(), "********");

		
		
		JSONObject requestingJson = new JSONObject();
		requestingJson.put("resp", encryptedRequest);

		log.info("object list ------------> " + objList);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestingJson.toString());
		log.info("response -------------------> " + requestEntity);
		return requestEntity;
	}	
}