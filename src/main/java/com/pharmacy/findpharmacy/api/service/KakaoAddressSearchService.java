package com.pharmacy.findpharmacy.api.service;

import com.pharmacy.findpharmacy.api.KakaoApiProperties;
import com.pharmacy.findpharmacy.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoAddressSearchService {

    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;
    private final KakaoApiProperties kakaoApiProperties;

    @Retryable(
        retryFor = {RuntimeException.class},
        maxAttempts = 2,
        backoff = @Backoff(delay = 2000)
    )
    public KakaoApiResponseDto requestAddressSearch(String address) {
        if (!StringUtils.hasText(address)) {
            return null;
        }
        URI uri = kakaoUriBuilderService.buildUriByAddressSearch(address);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiProperties.getKey());
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        // kakao api 호출
        ResponseEntity<KakaoApiResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class);
        return exchange.getBody();
    }

    @Recover
    public KakaoApiResponseDto recover(RuntimeException e, String address) {
        log.error("Kakao API 호출 중 에러가 발생했습니다. address: {}", address, e);
        return null;
    }

}
