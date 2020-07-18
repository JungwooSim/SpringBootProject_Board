package com.themoim.board.domain;

import com.themoim.board.dto.ReferenceCreateResponseDto;
import com.themoim.board.dto.ReferenceDeleteResponseDto;
import com.themoim.board.dto.ReferenceUpdateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "Reference")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long rId;

    private String title;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "written_by")
    private Long writtenBy;

    @Column(name = "is_deleted")
    private Long isDeleted = 0L;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public ReferenceUpdateResponseDto toUpdateResponse() {
        return ReferenceUpdateResponseDto.builder()
                .rId(rId)
                .title(title)
                .content(content)
                .writtenBy(writtenBy)
                .modifiedAt(modifiedAt)
                .createdAt(createdAt)
                .build();
    }

    public ReferenceCreateResponseDto toCreateResponseDto() {
        return ReferenceCreateResponseDto.builder()
                .rId(rId)
                .title(title)
                .content(content)
                .writtenBy(writtenBy)
                .modifiedAt(modifiedAt)
                .createdAt(createdAt)
                .isDeleted(isDeleted)
                .build();
    }

    public void deleteReference() {
        this.isDeleted = 1L;
    }

    public ReferenceDeleteResponseDto toDeleteResponse() {
        return ReferenceDeleteResponseDto.builder()
                .rId(rId)
                .title(title)
                .content(content)
                .writtenBy(writtenBy)
                .modifiedAt(modifiedAt)
                .createdAt(createdAt)
                .isDeleted(isDeleted)
                .build();
    }
}

