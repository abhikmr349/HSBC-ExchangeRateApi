package com.hsbc.abhishek.rates.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hsbc.abhishek.rates.dao.ExchangeRateDao;

@Service
public class ExchangeRateService {
	
	private final ExchangeRateDao exchangeRateDao;
	
	
	
	@Autowired
	public ExchangeRateService(@Qualifier("ExchangeDaoService") ExchangeRateDao exchangeRateDao) {
		this.exchangeRateDao=exchangeRateDao;
	}
	
	public void getCurrentExchangeRate(Model model) throws Exception {
		exchangeRateDao.getCurrentExchangeRate(model);
	}
	
	
	public void getHistoricalExchangeRate(Model model) throws Exception {
		exchangeRateDao.getHistoricalExchangeRate(model);
	}
}
