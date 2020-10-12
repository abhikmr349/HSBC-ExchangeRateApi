package com.hsbc.abhishek.rates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hsbc.abhishek.rates.api.ExchangeRateApiController;

@SpringBootTest
public class TestingWebApplicationTests {
	
	@Autowired
	ExchangeRateApiController exchangeRateApiController;

	@Test
	public void contextLoads() {
		assertThat(exchangeRateApiController).isNotNull();
	}

}