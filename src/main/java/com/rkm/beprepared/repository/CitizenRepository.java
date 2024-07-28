package com.rkm.beprepared.repository;

import com.rkm.beprepared.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    boolean existsByphone(String phone);

    Optional<Citizen> findCitizenByPhone(String phone);

    Optional<Citizen> findByOtp(String otp);

    List<Citizen> findAlByCityId(Long cityId);

    List<Citizen> findAllByCityProvinceId(Long provinceId);
}
