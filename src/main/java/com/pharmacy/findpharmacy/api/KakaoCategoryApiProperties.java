package com.pharmacy.findpharmacy.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kakao.rest.api.category-code")
public class KakaoCategoryApiProperties {

    private String pharmacy;
    private String hospital;

}
