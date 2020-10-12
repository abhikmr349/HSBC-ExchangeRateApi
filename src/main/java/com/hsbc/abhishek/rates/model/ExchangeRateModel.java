package com.hsbc.abhishek.rates.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateModel {
	
	private String base;
	private Map<String,String> rates;
	private String date;
	
	
	public ExchangeRateModel() {
	}


	public String getBase() {
		return base;
	}


	public void setBase(String base) {
		this.base = base;
	}


	public Map<String, String> getRates() {
		return rates;
	}


	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "ExchangeRateModel [base=" + base + ", rates=" + rates + ", date=" + date + "]";
	}


	

}
