package com.pharmacy.findpharmacy.pharmacy.service;

import com.pharmacy.findpharmacy.pharmacy.dto.PharmacyDto;
import com.pharmacy.findpharmacy.pharmacy.entity.Pharmacy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacySearchService {

    private final PharmacyRepositoryService pharmacyRepositoryService;

    public List<PharmacyDto> searchPharmaycyDtoList() {
        // redis

        // db
        return pharmacyRepositoryService.findAll().stream().map(this::convertToPharmacyDto).toList();
    }

    private PharmacyDto convertToPharmacyDto(Pharmacy pharmacy) {
        return PharmacyDto.builder()
            .id(pharmacy.getId())
            .pharmacyAddress(pharmacy.getPharmacyAddress())
            .pharmacyName(pharmacy.getPharmacyName())
            .latitude(pharmacy.getLatitude())
            .longitude(pharmacy.getLongitude())
            .build();
    }

}
