package com.pharmacy.findpharmacy.pharmacy.service

import com.pharmacy.findpharmacy.AbstractIntegrationContainerBaseTest
import com.pharmacy.findpharmacy.pharmacy.entity.Pharmacy
import com.pharmacy.findpharmacy.pharmacy.repository.PharmacyRepository
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepositoryService pharmacyRepositoryService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    def setup() {
        pharmacyRepository.deleteAll()
    }

    def "PharmacyRepository update -dirty checking success"() {
        given:
        String address = "서울 특별시 성동구 종암동"
        String modifiedAddress = "서울 특별시 성동구 구암시"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                .pharmacyName(name)
                .latitude(36.11)
                .longitude(128.11)
                .build()
        when:
        def pha = pharmacyRepository.save(pharmacy)
        pharmacyRepositoryService.updateAddress(pha.getId(), modifiedAddress);
        def result = pharmacyRepository.findById(pha.id).orElseThrow()
        then:
        result != null
        result.getPharmacyAddress() == modifiedAddress
    }

    def "PharmacyRepository update -dirty checking fail"() {
        given:
        String address = "서울 특별시 성동구 종암동"
        String modifiedAddress = "서울 특별시 성동구 구암시"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                .pharmacyName(name)
                .latitude(36.11)
                .longitude(128.11)
                .build()
        when:
        def pha = pharmacyRepository.save(pharmacy)
        pharmacyRepositoryService.updateAddressWithoutTransaction(pha.getId(), modifiedAddress);
        def result = pharmacyRepository.findById(pha.id).orElseThrow()
        then:
        result != null
        result.getPharmacyAddress() != modifiedAddress
    }

}