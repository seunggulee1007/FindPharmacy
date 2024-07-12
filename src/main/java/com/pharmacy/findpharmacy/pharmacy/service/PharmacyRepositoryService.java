package com.pharmacy.findpharmacy.pharmacy.service;

import com.pharmacy.findpharmacy.pharmacy.entity.Pharmacy;
import com.pharmacy.findpharmacy.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {

    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public void updateAddress(Long id, String address) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 약국이 존재하지 않습니다."));

        pharmacy.changePharmacyAddress(address);

    }

    public void updateAddressWithoutTransaction(Long id, String address) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 약국이 존재하지 않습니다."));

        pharmacy.changePharmacyAddress(address);
    }

    @Transactional(readOnly = true)
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

}
