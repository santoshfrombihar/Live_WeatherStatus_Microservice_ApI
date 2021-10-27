package com.weatherapi.controller;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapi.service.WeatherService;

@RestController
@RequestMapping("/getweather")
public class MainController {
	
	 @Autowired
	 private WeatherService weatherService;
	
	
		@GetMapping("/{city}")
		public String getWeather(@PathVariable("city") String city)
		{
			String JSON_SOURCE =  this.weatherService.getWeatherByCityName(city);
		    String res = null;
		    
			try {
				Map<String,Object> result = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
				         
				        for(Map.Entry<String,Object> entry : result.entrySet()) {
				        	 Object o = null;
				        	  if(entry.getKey().equals("main")) {
				                 o  = entry.getValue();
				                 String s = o.toString();
				                 
				                 int i = s.indexOf("temp");
				                 String ts[] = s.split(",");
				                 
				                 double tempCelcius = Math.abs(Double.parseDouble(ts[0].substring(6)) - 273.15 );
				                 tempCelcius = Double.parseDouble(String.format("%.2f", tempCelcius));
				                 
				        	   
				        	     ts[0] = "Temprature = "+ Double.toString(tempCelcius);
				 
				        	      res = "{"+ts[0]+"\n"+ts[4]+"\n"+ts[5];
				        	     break;
				              }  
				        }
				
				
				
				return  res;
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return  null;
		}

}
