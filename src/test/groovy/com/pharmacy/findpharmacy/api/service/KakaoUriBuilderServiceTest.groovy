package com.pharmacy.findpharmacy.api.service

import com.pharmacy.findpharmacy.api.KakaoApiUrlProperties
import spock.lang.Specification

import java.nio.charset.StandardCharsets


class KakaoUriBuilderServiceTest extends Specification {

    private KakaoUriBuilderService kakaoUriBuilderService;

    def setup() {
        def properties = new KakaoApiUrlProperties()
        properties.searchAddress = "https://dapi.kakao.com/v2/local/search/keyword.json";
        kakaoUriBuilderService = new KakaoUriBuilderService(properties);
    }

    def "buildUri() 메서드 테스트"() {
        given:
        String address = "서울 성북구"

        when:
        def uri = kakaoUriBuilderService.buildUriByAddressSearch(address);
        def decodedResult = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8)

        then:
        decodedResult == "https://dapi.kakao.com/v2/local/search/keyword.json?query=서울 성북구"

    }
}