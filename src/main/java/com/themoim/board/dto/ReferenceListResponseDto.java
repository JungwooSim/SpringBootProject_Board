package com.themoim.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


// TODO : page를 제네릭으로 처리하면 내용물만 바뀌면 됨
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
