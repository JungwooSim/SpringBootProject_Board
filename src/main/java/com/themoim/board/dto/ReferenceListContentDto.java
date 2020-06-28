package com.themoim.board.dto;

import com.themoim.board.domain.Reference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ReferenceListContentDto {
    private Long rId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long writtenBy;
    private Long isDeleted;

    public ReferenceListContentDto(Reference entity) {
        this.rId = entity.getRId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.writtenBy = entity.getWrittenBy();
        this.isDeleted = entity.getIsDeleted();
    }
}
