package com.themoim.board.service;

import com.themoim.board.domain.Reference;
import com.themoim.board.domain.ReferenceRepository;
import com.themoim.board.dto.ReferenceUpdateRequestDto;
import com.themoim.board.dto.ReferenceUpdateResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class ReferenceServiceTest {
    ReferenceService referenceService;

    @Mock
    ReferenceRepository referenceRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.referenceService = new ReferenceService(referenceRepository);
    }

    @Test
    public void update() {
        // given
        Long id = 1L;

        ReferenceUpdateRequestDto referenceUpdateRequestDto = ReferenceUpdateRequestDto.builder()
                .title("titlechange")
                .content("contentchange")
                .build();

        Reference reference = Reference.builder()
                .title("title")
                .content("content")
                .writtenBy(1L)
                .build();

        given(referenceRepository.findById(id)).willReturn(Optional.of(reference));

        reference.changeTitle(referenceUpdateRequestDto.getTitle());
        reference.changeContent(referenceUpdateRequestDto.getContent());

        given(referenceRepository.save(reference)).willReturn(reference);

        ReferenceUpdateResponseDto referenceUpdateResponseDto = reference.toUpdateResponse();

        // when
        referenceService.update(id, referenceUpdateRequestDto);

        // then
        assertThat(reference.getTitle(), is(referenceUpdateRequestDto.getTitle()));
        assertThat(reference.getContent(), is(referenceUpdateRequestDto.getContent()));
    }
}
