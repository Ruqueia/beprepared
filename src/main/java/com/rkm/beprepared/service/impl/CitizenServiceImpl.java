package com.rkm.beprepared.service.impl;

import com.rkm.beprepared.exception.BadRequestException;
import com.rkm.beprepared.exception.EntityNotFoundException;
import com.rkm.beprepared.model.Citizen;
import com.rkm.beprepared.model.City;
import com.rkm.beprepared.model.enums.Role;
import com.rkm.beprepared.repository.CitizenRepository;
import com.rkm.beprepared.service.CitizenService;
import com.rkm.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class CitizenServiceImpl implements CitizenService {

    private final PasswordEncoder passwordEncoder;
    private final LocationService locationService;
    private final CitizenRepository citizenRepository;
    @Override
    @Transactional
    public String createCitizen(Citizen citizen, Long cityId) {
        if (citizenRepository.existsByphone(citizen.getPhone())){
            throw new BadRequestException("ja existe um cidadao com esse numero");
        }
        City city =locationService.getCityById(cityId);
        citizen.setCity(city);
        citizen.setVerified(false);
        citizen.setRole(Role.USER);
        citizen.setOtp(generateOtp(6));
        var savedCitizen = citizenRepository.save(citizen);
        return "Cidadao criado com sucesso! O seu código de verificaçao é: "+ savedCitizen.getOtp();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizens() {

        return citizenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByCityId(Long cityId) {

        return citizenRepository.findAlByCityId(cityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByProvinceId(Long provinceId) {

        return citizenRepository.findAllByCityProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Citizen getCitizenById(Long id) {

        return citizenRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Cidadao nao encontrado!"));
    }

    @Override
    @Transactional
    public String verifyAccount(String otp) {
        Citizen citizen = citizenRepository.findByOtp(otp).orElseThrow(()->
                new EntityNotFoundException("Cidadao nao encontrado!"));
        citizen.setVerified(true);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return "A tua conta foi criada com sucesso!";
    }

    @Override
    @Transactional
    public String generateOTPForCitizen(String phone) {
        Citizen citizen = citizenRepository.findCitizenByPhone(phone).orElseThrow(() ->
                new EntityNotFoundException("Cidadao nao encontrado, nao foi possível gerar seu código!"));
        citizen.setOtp(null);
        String otp = generateOtp(6);
        citizen.setOtp(passwordEncoder.encode(otp));
        return "O seu código de acesso é: " + otp;
    }

    private static String generateOtp(int length){
        String otp="";
        int x;
        char[] chars=new char[length];

        for(int i=0;i<length;i++){
            Random random = new Random();
            x=random.nextInt(9);
            chars[i]=Integer.toString(x).toCharArray()[0];
        }

        otp=new String(chars);
        return otp.trim();
    }
}
