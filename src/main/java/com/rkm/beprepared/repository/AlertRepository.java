package com.rkm.beprepared.repository;

import com.rkm.beprepared.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByActive(boolean isActive);

    List<Alert> findAllByActiveAndCityId(boolean isActive, Long cityId);

    long countByActive(boolean isActive);

    List<Alert> findAllByActiveAndProvinceId(boolean isActive, Long provinceId);
}
