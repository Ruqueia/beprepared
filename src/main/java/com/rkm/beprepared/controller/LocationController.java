package com.rkm.beprepared.controller;



import com.rkm.beprepared.dto.response.CityResponseDto;
import com.rkm.beprepared.dto.response.ProvinceResponseDto;
import com.rkm.beprepared.mapper.Mapper;
import com.rkm.beprepared.service.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@Tag(name = "4. Location Controller")
public class LocationController {
    private final Mapper mapper;
    private final LocationService locationService;

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceResponseDto>> getAllProvinces(){
          return ResponseEntity.ok(mapper.mapProvinceToREsponseDtoList(
                  locationService.getAllProvinces()
          ));
    }

    @GetMapping("/province") /*pelo id da provincia*/
    public ResponseEntity<ProvinceResponseDto> getProvinceById(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapProvinceToResponseDto(
                locationService.getProvinceById(id)
        ));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseDto>> getAllCities(){
        return ResponseEntity.ok(mapper.mapCityToResponseDtoList(
                locationService.getAllCities()
        ));
    }

    @GetMapping("/cities/{provinceId}") /*pelo id da provincia*/
    public ResponseEntity<List<CityResponseDto>> getCityById(@PathVariable Long provinceId){
      return ResponseEntity.ok(mapper.mapCityToResponseDtoList(
              locationService.getAllCitiesProvinceId(provinceId)
      ));
    }

    @GetMapping("/city") /*pelo id da cidade*/
    public ResponseEntity<CityResponseDto> getCityByProvinceId(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCityToResponseDto(
                locationService.getCityById(id)
        ));
    }

}
