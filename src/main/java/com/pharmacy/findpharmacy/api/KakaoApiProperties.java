package com.pharmacy.findpharmacy.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kakao.rest.api")
public class KakaoApiProperties {

    private String key;

}
