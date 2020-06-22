package com.themoim.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Table(name = "Reference")
@Entity
@NoArgsConstructor
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long rId;

    private String title;

    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", insertable = false, updatable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "written_by")
    private Long writtenBy;

    @Column(name = "is_deleted")
    private Long isDeleted = 0L;

    @Builder
    public Reference(String title, String content, Long writtenBy) {
        this.title = title;
        this.content = content;
        this.writtenBy = writtenBy;
    }
}
