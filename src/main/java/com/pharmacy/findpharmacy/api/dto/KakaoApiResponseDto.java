package com.pharmacy.findpharmacy.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoApiResponseDto {

    private MetaDto metaDto;

    private List<DocumentDto> documentDtoList;

}
