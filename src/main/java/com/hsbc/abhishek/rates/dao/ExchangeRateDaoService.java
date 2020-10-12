package com.hsbc.abhishek.rates.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.hsbc.abhishek.rates.exception.RateNotFoundException;
import com.hsbc.abhishek.rates.model.ExchangeRateModel;
import com.hsbc.abhishek.rates.service.ExchangeRateService;


@Repository("ExchangeDaoService")
public class ExchangeRateDaoService implements ExchangeRateDao{
	
	Logger logger =LoggerFactory.getLogger(ExchangeRateService.class);
	
	private String currentRateEndpoint="https://api.ratesapi.io/api/latest?base=EUR&symbols=GBP,USD,HKD";
	private String historicalRateEndpointBaseURI="https://api.ratesapi.io/api/";
	private String historicalRateEndpointURIparams="?base=EUR&symbols=GBP,USD,HKD";

	@Override
	public void getCurrentExchangeRate(Model model) throws Exception{
	logger.info("Before Calling getExchangeRate Method inside getCurrentExchangeRate()");
	final ExchangeRateModel exchangeRateModel=getExchangeRate(currentRateEndpoint);
	logger.info("Exchange Rate Model is: "+exchangeRateModel.toString());	
		System.out.println("Response Body is base:"+exchangeRateModel);
		
		
		model.addAttribute("Rates", exchangeRateModel.getRates());
		model.addAttribute("Base",  exchangeRateModel.getBase());
		model.addAttribute("Date",  exchangeRateModel.getDate());
		
	}
	
	@Override
	public ExchangeRateModel getExchangeRate(String endpoint) throws RateNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity =new HttpEntity<>("parameters", headers);
		final ResponseEntity<ExchangeRateModel> responseEntity = new RestTemplate().exchange(
				endpoint,HttpMethod.GET,entity, ExchangeRateModel.class);
		logger.info("Inside getExchangeRate method ");
		logger.info("Response Entity value is: "+responseEntity.toString());
		
		if(!responseEntity.hasBody()) {
			throw new RateNotFoundException("Sorry we are not able to fetch your request, Please try again!!!");
		}
		else if(responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().isError())
		{
			throw new RateNotFoundException("Sorry something went wrong, please try again later!!!");
		}
		
		return responseEntity.getBody();
		
	}

	@Override
	public void getHistoricalExchangeRate(Model model) throws Exception{
		int i=0;
		ArrayList<String> pastDatesList = new ArrayList<String>();
		ArrayList<ExchangeRateModel> ermArrayList=new ArrayList<ExchangeRateModel>();
		LocalDate currDate= LocalDate.now();
		System.out.println("Date is :"+currDate);
		
		logger.info("Before Calling getExchangeRate Method inside getHistoricalExchangeRate()");

		
		while(i<6) {
			pastDatesList.add(currDate.toString());
			logger.info("Date Values in Historical Rest API Call is: "+currDate.toString());
			ermArrayList.add(getExchangeRate(historicalRateEndpointBaseURI+currDate.toString()+historicalRateEndpointURIparams));
			currDate=currDate.minusMonths(1);
			i++;
			}
		
		logger.info("Historical Rate Model Object is: "+ermArrayList);
		model.addAttribute("historicalRateModelObject", ermArrayList);

}
}	
