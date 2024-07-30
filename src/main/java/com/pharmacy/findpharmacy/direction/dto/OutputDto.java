package com.pharmacy.findpharmacy.direction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class OutputDto {

    private String pharmacyName;

    private String pharmacyAddress;

    private String directionUrl;

    private String roadViewUrl;

    private String distance;

}
