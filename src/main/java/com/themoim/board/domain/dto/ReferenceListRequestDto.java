package com.themoim.board.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReferenceListRequestDto {
    private Integer page = 1;
    private Integer size = 3;

    @Builder
    public ReferenceListRequestDto(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
