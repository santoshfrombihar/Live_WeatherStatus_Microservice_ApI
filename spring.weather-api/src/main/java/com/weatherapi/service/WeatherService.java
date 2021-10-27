package com.weatherapi.service;

import org.springframework.stereotype.Service;

@Service
public interface WeatherService {

	  public String getWeatherByCityName(String city);
}
