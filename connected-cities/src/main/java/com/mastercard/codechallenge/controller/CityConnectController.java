package com.mastercard.codechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.codechallenge.bean.City;
import com.mastercard.codechallenge.service.CityService;

@RestController
public class CityConnectController {
	
	@Autowired
	private CityService cityService;

	@GetMapping("/connected")
	public ResponseEntity<Object> connected(@RequestParam("origin")String origin, @RequestParam("destination")String destination) {
		String resp = cityService.isConnected(new City(origin.trim().toUpperCase()), new City(destination.trim().toUpperCase()));
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
	}
}
