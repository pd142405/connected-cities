package com.mastercard.codechallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.codechallenge.bean.City;
import com.mastercard.codechallenge.bean.Graph;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private Graph g;

	@Override
	public String isConnected(City src, City dest) {
		boolean result = g.isConnected(src, dest);
		String resp = null;
		if(result)
			resp = "Yes";
		else 
			resp = "No";
		return resp;
	}

}
