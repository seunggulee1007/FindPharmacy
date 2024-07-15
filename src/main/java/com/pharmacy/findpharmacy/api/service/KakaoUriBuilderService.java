package com.pharmacy.findpharmacy.api.service;

import com.pharmacy.findpharmacy.api.KakaoApiUrlProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoUriBuilderService {

    private final KakaoApiUrlProperties kakaoApiUrlProperties;

    public URI buildUriByAddressSearch(String address) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(kakaoApiUrlProperties.getSearchAddress());
        uriBuilder.queryParam("query", address);

        URI uri = uriBuilder.build().encode().toUri();
        log.info("[KakaoUriBuilderService buildUriByAddressSearch] address : {}, uri: {}", address, uri);
        return uri;
    }

    /**
     * 공공데이터 포털을 이용하는 것이 아닌 카카오 API를 이용하여 주변 약국을 검색하기 위한 URI를 생성한다.
     *
     * @param latitude  위도
     * @param longitude 경도
     * @param radius    반경
     * @param category  카테고리
     * @return
     */
    public URI builderUriByCategorySearch(double latitude, double longitude, double radius, String category) {
        double meterRadius = radius * 1000;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(kakaoApiUrlProperties.getCategoryAddress());

        uriBuilder.queryParam("x", longitude);
        uriBuilder.queryParam("y", latitude);
        uriBuilder.queryParam("radius", meterRadius);
        uriBuilder.queryParam("category_group_code", category);
        uriBuilder.queryParam("sort", "distance");

        URI uri = uriBuilder.build().encode().toUri();

        log.info("[KakaoUriBuilderService buildUriBy] uri : {}", uri);
        return uri;
    }

}
