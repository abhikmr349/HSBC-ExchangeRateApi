package com.hsbc.abhishek.rates.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsbc.abhishek.rates.service.ExchangeRateService;


@Controller
@RequestMapping("/api")
public class ExchangeRateApiController {
	
	
	private final ExchangeRateService exchangeRateService;
	
	Logger logger= LoggerFactory.getLogger(ExchangeRateApiController.class);
	
	@Autowired
	public ExchangeRateApiController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService=exchangeRateService;
	}
	
	@RequestMapping(value = "/getcurrentrate", method = RequestMethod.GET)
	public String getCurrentRates(Model model) throws Exception {
		logger.info("In getCurrentRates Method");
		exchangeRateService.getCurrentExchangeRate(model);
		return "getcurrentrate";
	}
	
	@RequestMapping (value = "/gethistoricalrate", method = RequestMethod.GET)
	public String getHistoricalRates(Model model) throws Exception {
		logger.info("In getHistoricalRate Method");
		exchangeRateService.getHistoricalExchangeRate(model);
		return "gethistoricalrate";
	}
	

}
