package com.mastercard.codechallenge.bean;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.springframework.stereotype.Component;

@Component
public class Graph {

private HashMap<City, LinkedList<City>> adjCities;
	
	public Graph() {
		adjCities = new HashMap<City, LinkedList<City>>();
		List<City> cities = getCityList();
		for(City c : cities) {
			addCity(c);
		}
		HashMap<City, City> edges = getEdgeList();
		for(Entry<City, City> entry : edges.entrySet()) {
			addEdge(entry.getKey(), entry.getValue());
		}
	}
	
	
	public HashMap<City, LinkedList<City>> getAdjCities() {
		return adjCities;
	}


	private HashMap<City, City> getEdgeList() {
		Path path = Paths.get("city.txt");
		Charset cs = Charset.forName("US-ASCII");
		HashMap<City, City> edges = new HashMap<City, City>();
		try (BufferedReader reader = Files.newBufferedReader(path,cs)){
			String line = null;
			String [] split;
			while((line=reader.readLine()) != null) {
				split = line.split(",");
				edges.put(new City(split[0].trim().toUpperCase()), new City(split[1].trim().toUpperCase()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edges;
	}

	private void addCity(City c) {
		adjCities.putIfAbsent(c, new LinkedList<City>());
		
	}

	void addEdge(City src, City dest) {
		adjCities.get(src).add(dest);
		adjCities.get(dest).add(src);
	}
	
	private List<City> getCityList() {
		Path path = Paths.get("city.txt");
		Charset cs = Charset.forName("US-ASCII");
		Set<City> cities = new HashSet<City>();
		try (BufferedReader reader = Files.newBufferedReader(path,cs)){
			String line = null;
			String [] split;
			while((line=reader.readLine()) != null) {
				split = line.split(",");
				cities.add(new City(split[0].trim().toUpperCase()));
				cities.add(new City(split[1].trim().toUpperCase()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<City>(cities);
	}
	
	public Set<City> dfcTraversal(Graph g, City root) {
		Set<City> visited = new LinkedHashSet<City>();
		Stack<City> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			City city = stack.pop();
			if(!visited.contains(city)) {
				visited.add(city);
				if (null != g.getAdjCities(city)) {
					for (City c : g.getAdjCities(city)) {
						stack.push(c);
					}
				}
			}
		}
		return visited;
	}

	private List<City> getAdjCities(City city) {
		return adjCities.get(city);
	}

	public boolean isConnected(City src, City dest) {
		Set<City> connectedCities = dfcTraversal(this, src);
		boolean result = false;
		if(connectedCities.contains(dest)) {
			result = true;
		}
		return result;
	}
}
