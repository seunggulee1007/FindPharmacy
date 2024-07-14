package com.pharmacy.findpharmacy.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaDto {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("is_end")
    private boolean end;

    @JsonProperty("pageable_count")
    private Integer pageableCount;

    public MetaDto(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
