package com.themoim.board.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ReferenceListResponseDto {

    private Integer page;
    private Integer size;

    @JsonProperty(value = "total_count")
    private Long totalCount;

    private List<ReferenceListContentDto> referenceListContentDto;

    @Builder
    public ReferenceListResponseDto(Integer page, Integer size, Long totalCount, List<ReferenceListContentDto> referenceListContentDto) {
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.referenceListContentDto = referenceListContentDto;
    }
}
