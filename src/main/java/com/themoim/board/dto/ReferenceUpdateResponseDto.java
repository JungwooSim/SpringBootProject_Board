package com.themoim.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceUpdateResponseDto {

    @JsonProperty(value = "r_id")
    private Long rId;

    private String title;

    private String content;

    @JsonProperty(value = "create_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "modified_at")
    private LocalDateTime modifiedAt;

    @JsonProperty(value = "written_by")
    private Long writtenBy;
}
