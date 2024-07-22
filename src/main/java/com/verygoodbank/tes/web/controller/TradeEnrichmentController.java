package com.verygoodbank.tes.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class TradeEnrichmentController {

	Logger logger = LoggerFactory.getLogger(TradeEnrichmentController.class);

	@RequestMapping(value = "/enrich", method = RequestMethod.POST)
	public String enrich() {

		logger.debug("Received enrich request");

		return "OK";
	}

}


