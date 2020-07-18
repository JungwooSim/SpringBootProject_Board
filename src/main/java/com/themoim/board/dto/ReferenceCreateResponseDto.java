package com.themoim.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceCreateResponseDto {
    private Long rId;

    private String title;

    private String content;

    private List<ReferenceFileContentDto> files;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "modified_at")
    private LocalDateTime modifiedAt;

    @JsonProperty(value = "written_by")
    private Long writtenBy;

    @JsonProperty(value = "is_deleted")
    private Long isDeleted;
}
