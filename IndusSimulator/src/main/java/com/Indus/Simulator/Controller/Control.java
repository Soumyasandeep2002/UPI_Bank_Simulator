package com.Indus.Simulator.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Indus.Simulator.Service.ServiceImpl;


@RestController
public class Control {

	private static final Logger log = LogManager.getLogger(Control.class);

	@Autowired
	private ServiceImpl service;

	@GetMapping("/")
	public String data(){
		return service.Welcome();
	}
	

	@PostMapping("/decrypt-data")
	public HttpEntity<String> checkVpa(@RequestBody String encryptedData)
	{			
		log.info("encryptedData --------------------> " + encryptedData);
		return service.vpa(encryptedData);
	}
}
