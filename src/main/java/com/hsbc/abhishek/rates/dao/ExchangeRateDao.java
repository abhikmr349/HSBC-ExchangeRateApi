package com.hsbc.abhishek.rates.dao;

import org.springframework.ui.Model;

import com.hsbc.abhishek.rates.exception.RateNotFoundException;
import com.hsbc.abhishek.rates.model.ExchangeRateModel;

public interface ExchangeRateDao {
	
	
	void getCurrentExchangeRate(Model model) throws Exception;
	
	void getHistoricalExchangeRate(Model model) throws Exception;
	
	ExchangeRateModel getExchangeRate(String endpoint) throws RateNotFoundException;

}
