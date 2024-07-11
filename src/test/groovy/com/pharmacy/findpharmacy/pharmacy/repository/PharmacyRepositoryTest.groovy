package com.pharmacy.findpharmacy.pharmacy.repository

import com.pharmacy.findpharmacy.AbstractIntegrationContainerBaseTest
import com.pharmacy.findpharmacy.pharmacy.entity.Pharmacy
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRepositoryTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    def setup() {
        pharmacyRepository.deleteAll()
    }

    def "PharmacyRepository save"() {
        given:
        String address = "서울 특별시 성동구 종암동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11
        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                .pharmacyName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when:
        def result = pharmacyRepository.save(pharmacy);
        println result

        then:
        result.getPharmacyAddress() == address
        result.getPharmacyName() == name
        result.latitude == latitude
        result.longitude == longitude

    }

    def "PharmacyRepository saveAll"() {
        given:
        String address = "서울 특별시 성동구 종암동"
        String name = "은혜 약국"
        double latitude = 36.11
        double longitude = 128.11
        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(address)
                .pharmacyName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when:
        pharmacyRepository.saveAll(List.of(pharmacy))
        def pharmacies = pharmacyRepository.findAll()

        then:
        pharmacies.size() == 1
    }


}