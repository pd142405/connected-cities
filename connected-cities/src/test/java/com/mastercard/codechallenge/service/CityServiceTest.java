package com.mastercard.codechallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastercard.codechallenge.bean.City;
import com.mastercard.codechallenge.config.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CityServiceTest {
	
	@Autowired
	private CityService cityService;
	
	@Test
	public void testDirectlyConnectedCities() {
		
		String result = cityService.isConnected(new City("BOSTON"), new City("NEWARK"));
		assertEquals("Yes", result);
	}
	
	@Test
	public void testInDirectlyConnectedCities() {
		
		String result = cityService.isConnected(new City("EDISON"), new City("PHILADELPHIA"));
		assertEquals("Yes", result);
	}
	
	@Test
	public void testNotConnectedCities() {
		
		String result = cityService.isConnected(new City("EDISON"), new City("TRENTON"));
		assertEquals("No", result);
	}
	
	@Test
	public void testOneCityNotPresent() {
		
		String result = cityService.isConnected(new City("EDISON"), new City("ATLANTA"));
		assertEquals("No", result);
	}
	
	@Test
	public void testBothCityNotPresent() {
		
		String result = cityService.isConnected(new City("MIAMI"), new City("ATLANTA"));
		assertEquals("No", result);
	}
	

}
