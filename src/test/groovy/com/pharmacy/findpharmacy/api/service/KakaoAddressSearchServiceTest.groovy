package com.pharmacy.findpharmacy.api.service

import com.pharmacy.findpharmacy.AbstractIntegrationContainerBaseTest
import com.pharmacy.findpharmacy.api.dto.KakaoApiResponseDto
import org.springframework.beans.factory.annotation.Autowired

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private KakaoAddressSearchService kakaoAddressSearchService;

    def "address 파라미터 값이 null이면, requestAddressSearch 메소드는 null을 리턴한다"() {
        given:
        String address = null;

        when:
        def kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        then:
        kakaoApiResponseDto == null
    }


    def "카카오 주소 검색 API 호출 테스트"() {
        given:
        String address = "서울특별시 강남구 역삼동 736-1";

        when:
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        then:
        kakaoApiResponseDto != null
        kakaoApiResponseDto.getMetaDto().getTotalCount() > 0
        kakaoApiResponseDto.getDocumentDtoList().size() > 0
    }

}