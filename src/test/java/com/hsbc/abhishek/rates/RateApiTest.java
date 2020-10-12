package com.hsbc.abhishek.rates;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RateApiTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	//Test Method for Current Exchange Rates
	@Test
	public void getCurrentRateApiShouldReturnOk() throws Exception {
		
		assertTrue(this.restTemplate.withBasicAuth("admin", "admin").getForEntity("http://localhost:"+port+"/api/getcurrentrate", String.class).getStatusCode().is2xxSuccessful());
		
	}
	
	//Test Method for Historical Exchange Rates
	@Test
	public void getHistoricalRateApiShouldReturnOk() throws Exception {
		
		assertTrue(this.restTemplate.withBasicAuth("admin", "admin").getForEntity("http://localhost:"+port+"/api/getcurrentrate", String.class).getStatusCode().is2xxSuccessful());
	
	}
}