package com.weatherapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {



	  public String getWeatherByCityName(String city)
	  {
		  
		  String newd = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=6129e2e97dc489bdcc21b90aee0b6fda";
		  RestTemplate restTemplate  = new RestTemplate();
		
		  return restTemplate.getForObject(newd, String.class);
		 
	  }
}

