package com.pharmacy.findpharmacy.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentDto {

    @JsonProperty("place_name")
    protected String placeName;

    @JsonProperty("address_name")
    protected String addressName;

    @JsonProperty("y")
    protected double latitude;

    @JsonProperty("x")
    protected double longitude;

    protected double distance;

}
