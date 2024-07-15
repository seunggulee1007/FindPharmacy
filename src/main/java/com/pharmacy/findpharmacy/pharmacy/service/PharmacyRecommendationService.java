package com.pharmacy.findpharmacy.pharmacy.service;

import com.pharmacy.findpharmacy.api.dto.DocumentDto;
import com.pharmacy.findpharmacy.api.dto.KakaoApiResponseDto;
import com.pharmacy.findpharmacy.api.service.KakaoAddressSearchService;
import com.pharmacy.findpharmacy.direction.entity.Direction;
import com.pharmacy.findpharmacy.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacy(String address) {
        // 주소를 기준으로 카카오 API를 호출하여 위도 경도를 가져옴
        // 위도 경도를 기준으로 약국을 추천
        // 추천된 약국을 저장
        log.info("약국 추천 서비스 호출. address: {}", address);
        KakaoApiResponseDto responseDto = searchAndGetKakaoApiResponseDto(address);
        if (responseDto == null)
            return;
        DocumentDto documentDto = responseDto.getDocumentDtoList().get(0);
        List<Direction> directions = directionService.buildDirectionList(documentDto);
        directionService.saveAll(directions);

    }

    private KakaoApiResponseDto searchAndGetKakaoApiResponseDto(String address) {
        Optional<KakaoApiResponseDto> optionalKakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);
        if (optionalKakaoApiResponseDto.isEmpty() || CollectionUtils.isEmpty(optionalKakaoApiResponseDto.get().getDocumentDtoList())) {
            log.error("카카오 API 호출 결과가 없습니다. address: {}", address);
            return null;
        }
        KakaoApiResponseDto responseDto = optionalKakaoApiResponseDto.get();
        return responseDto;
    }

}
