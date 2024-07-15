package com.pharmacy.findpharmacy.api.service;

import com.pharmacy.findpharmacy.api.KakaoApiProperties;
import com.pharmacy.findpharmacy.api.KakaoCategoryApiProperties;
import com.pharmacy.findpharmacy.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoCategorySearchService {

    private final KakaoUriBuilderService kakaoUriBuilderService;
    private final RestTemplate restTemplate;
    private final KakaoApiProperties kakaoApiProperties;
    private final KakaoCategoryApiProperties kakaoCategoryApiProperties;

    public KakaoApiResponseDto requestPharmacyCategorySearch(double latitude, double longitude, double radius) {
        URI uri = kakaoUriBuilderService.builderUriByCategorySearch(latitude, longitude, radius, kakaoCategoryApiProperties.getPharmacy());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiProperties.getKey());
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
    }

}
