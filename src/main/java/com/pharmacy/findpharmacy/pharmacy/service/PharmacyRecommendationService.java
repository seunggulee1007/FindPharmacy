package com.pharmacy.findpharmacy.pharmacy.service;

import com.pharmacy.findpharmacy.api.dto.DocumentDto;
import com.pharmacy.findpharmacy.api.dto.KakaoApiResponseDto;
import com.pharmacy.findpharmacy.api.service.KakaoAddressSearchService;
import com.pharmacy.findpharmacy.direction.dto.OutputDto;
import com.pharmacy.findpharmacy.direction.entity.Direction;
import com.pharmacy.findpharmacy.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public List<OutputDto> recommendPharmacyList(String address) {
        // 주소를 기준으로 카카오 API를 호출하여 위도 경도를 가져옴
        // 위도 경도를 기준으로 약국을 추천
        // 추천된 약국을 저장
        log.info("약국 추천 서비스 호출. address: {}", address);
        Optional<KakaoApiResponseDto> responseDto = searchAndGetKakaoApiResponseDto(address);
        if (responseDto.isEmpty())
            return Collections.emptyList();
        DocumentDto documentDto = responseDto.get().getDocumentDtoList().get(0);
        List<Direction> directions = directionService.buildDirectionList(documentDto);
        return directionService.saveAll(directions).stream().map(this::convertToOutputDto).toList();
    }

    private Optional<KakaoApiResponseDto> searchAndGetKakaoApiResponseDto(String address) {
        Optional<KakaoApiResponseDto> optionalKakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);
        if (optionalKakaoApiResponseDto.isEmpty() || CollectionUtils.isEmpty(optionalKakaoApiResponseDto.get().getDocumentDtoList())) {
            log.error("카카오 API 호출 결과가 없습니다. address: {}", address);
            return Optional.empty();
        }
        return optionalKakaoApiResponseDto;
    }

    private OutputDto convertToOutputDto(Direction direction) {
        return OutputDto.builder()
            .pharmacyName(direction.getTargetPharmacyName())
            .pharmacyAddress(direction.getTargetAddress())
            .directionUrl("todo")
            .roadViewUrl("todo")
            .distance(String.format("%.2f km", direction.getDistance()))
            .build();
    }

}
