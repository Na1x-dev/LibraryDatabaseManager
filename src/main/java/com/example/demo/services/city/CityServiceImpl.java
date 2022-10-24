package com.example.demo.services.city;

import com.example.demo.models.City;
import com.example.demo.models.Client;
import com.example.demo.repositories.city.CityJpaRepository;
import com.example.demo.repositories.client.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityJpaRepository cityRepository;

    @Override
    public City create(City city) {
        city.setTitle(city.getTitle().toLowerCase().trim());
        return cityRepository.save(city);
    }

    @Override
    public List<City> readAll() {
        return cityRepository.findAll();
    }

    @Override
    public City readByCityTitle(String title) {
        return cityRepository.findCityByTitle(title.toLowerCase().trim());
    }
}
