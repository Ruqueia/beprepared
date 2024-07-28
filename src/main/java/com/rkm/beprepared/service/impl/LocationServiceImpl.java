package com.rkm.beprepared.service.impl;

import com.rkm.beprepared.model.City;
import com.rkm.beprepared.model.Province;
import com.rkm.beprepared.repository.CityRepository;
import com.rkm.beprepared.repository.ProvinceRepository;
import com.rkm.beprepared.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCitiesProvinceId(Long provinceId) {
        return cityRepository.findAllByProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Province getProvinceById(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(()->
                new EntityNotFoundException("A provincia nao foi encontrada!"));
    }

    @Override
    @Transactional(readOnly = true)
    public City getCityById(Long cityId) {

        return cityRepository.findById(cityId).orElseThrow(()->
                new EntityNotFoundException("O distrito nao foi encontrada!"));
    }
}
