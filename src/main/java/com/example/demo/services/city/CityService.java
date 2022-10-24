package com.example.demo.services.city;

import com.example.demo.models.City;
import com.example.demo.models.Client;

import java.util.List;

public interface CityService {
    City create(City city);

    List<City> readAll();

    City readByCityTitle(String title);
}
