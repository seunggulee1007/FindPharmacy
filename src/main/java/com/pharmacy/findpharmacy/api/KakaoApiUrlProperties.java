package com.pharmacy.findpharmacy.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kakao.rest.api.url")
public class KakaoApiUrlProperties {

    private String searchAddress;

}
