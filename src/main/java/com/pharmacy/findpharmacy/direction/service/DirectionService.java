package com.pharmacy.findpharmacy.direction.service;

import com.pharmacy.findpharmacy.api.dto.DocumentDto;
import com.pharmacy.findpharmacy.direction.entity.Direction;
import com.pharmacy.findpharmacy.pharmacy.service.PharmacyRepositoryService;
import com.pharmacy.findpharmacy.pharmacy.service.PharmacySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionService {

    private static final int MAX_SEARCH_COUNT = 3;  // 약국 최대 검색 개수
    private static final double RADIUS_KM = 10.0;    // 반경 10km
    private final PharmacySearchService pharmacySearchService;
    private final PharmacyRepositoryService pharmacyRepositoryService;

    public List<Direction> buildDirectionList(DocumentDto documentDto) {
        if (documentDto == null) {
            return Collections.emptyList();
        }
        // 고객의 위도 경도를 기준으로 조회해야 하므로 약국 데이터 조회
        // 거리계산 알고리즌ㅁ을 이용해서 고객과 약국 사이의 거리를 계산하고 sort
        return pharmacySearchService.searchPharmaycyDtoList()
            .stream().map(pharmacyDto -> Direction.builder()
                .inputAddress(documentDto.getAddressName())
                .inputLatitude(documentDto.getLatitude())
                .inputLongitude(documentDto.getLongitude())
                .targetPharmacyName(pharmacyDto.getPharmacyName())
                .targetAddress(pharmacyDto.getPharmacyAddress())
                .targetLatitude(pharmacyDto.getLatitude())
                .targetLongitude(pharmacyDto.getLongitude())
                .distance(calculateDistance(
                    documentDto.getLatitude(), documentDto.getLongitude(),
                    pharmacyDto.getLatitude(), pharmacyDto.getLongitude()
                ))
                .build())
            .filter(direction -> direction.getDistance() <= RADIUS_KM)
            .sorted(Comparator.comparing(Direction::getDistance))
            .limit(MAX_SEARCH_COUNT)
            .toList();
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371.01;

        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }

}
