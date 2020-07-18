package com.themoim.board.dto;

import com.themoim.board.domain.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceUpdateRequestDto {
    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 50, message = "제목은 50글자 내외로 입력해주시기 바랍니다.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    // TODO :: 사용자 데이터를 임시로 넣어줌. 나중이 삭제 필요
    private Long writtenBy = 1L;

    public Reference toEntity() {
        return Reference.builder()
                .title(title)
                .content(content)
                .writtenBy(writtenBy)
                .build();
    }
}
