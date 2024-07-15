package com.pharmacy.findpharmacy.api.service

import com.pharmacy.findpharmacy.AbstractIntegrationContainerBaseTest
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
        kakaoApiResponseDto == Optional.empty()
    }


    def "카카오 주소 검색 API 호출 테스트"() {
        given:
        String address = "서울특별시 강남구 역삼동 736-1";

        when:
        def kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        then:
        kakaoApiResponseDto != Optional.empty()
        kakaoApiResponseDto.get().getMetaDto().getTotalCount() > 0
        kakaoApiResponseDto.get().getDocumentDtoList().size() > 0
    }

    def "정상적인 주소를 입력했을 경우, 정상적으로 위도 경도를 변환 된다."() {
        given:
        boolean actualResult = false

        when:
        def searchResult = kakaoAddressSearchService.requestAddressSearch(inputAddress)

        then:
        if (searchResult == null) actualResult = false
        else actualResult = searchResult.get().getDocumentDtoList().size() > 0
        where:
        inputAddress         | expectedREsult
        "서울 특별시 성북구 종암동"     | true
        "서울 성북구 종암동 91"      | true
        "서울 대학로"             | true
        "서울 성북구 종암동 잘못된 주소"  | false
        "광진구 구의동 251045"     | true
        "광진구 구의동 251-455555" | false

    }
}