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

}
