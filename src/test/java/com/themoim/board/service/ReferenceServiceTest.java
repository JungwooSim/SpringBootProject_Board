package com.themoim.board.service;

import com.themoim.board.domain.Reference;
import com.themoim.board.domain.ReferenceFileLink;
import com.themoim.board.domain.ReferenceFileLinkRepository;
import com.themoim.board.domain.ReferenceRepository;
import com.themoim.board.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;

public class ReferenceServiceTest {
    ReferenceService referenceService;

    @Mock
    ReferenceRepository referenceRepository;

    @Mock
    ReferenceFileLinkRepository referenceFileLinkRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.referenceService = new ReferenceService(referenceRepository, referenceFileLinkRepository);
    }

    @Test
    public void insert_파일없을때() {
        // given
        ReferenceCreateRequestDto referenceCreateRequestDto = ReferenceCreateRequestDto.builder()
                .title("6")
                .content("dddddddd")
                .build();

        Reference reference = referenceCreateRequestDto.toEntity();

        // when
        given(referenceRepository.save(reference)).willReturn(reference);

        ReferenceCreateResponseDto referenceCreateResponseDto = referenceService.create(referenceCreateRequestDto);

        // then
        assertThat(referenceCreateResponseDto.getFiles(), is(nullValue()));
    }

    @Test
    public void insert_파일있을때() {
        // given
        List<HashMap<String, String>> files = new ArrayList();
        files.add(
                new HashMap<String, String>() {
                    {
                        put("filePath","/Users/bigpenguin/file/test1.xls");
                    }
                }
        );
        files.add(
                new HashMap<String, String>() {
                    {
                        put("filePath","/Users/bigpenguin/file/test2.xls");
                    }
                }
        );
        ReferenceCreateRequestDto referenceCreateRequestDto = ReferenceCreateRequestDto.builder()
                .title("6")
                .content("dddddddd")
                .files(files)
                .build();

        Reference reference = referenceCreateRequestDto.toEntity();

        List<ReferenceFileLink> referenceFileLink = new ArrayList<>();

        referenceCreateRequestDto.getFiles().forEach(
                file -> {
                    referenceFileLink.add(
                            ReferenceFileLink.builder().link(file.get("filePath")).reference(reference).build()
                    );
                }
        );

        // when
        given(referenceRepository.save(reference)).willReturn(reference);
        given(referenceFileLinkRepository.saveAll(referenceFileLink)).willReturn(referenceFileLink);

        ReferenceCreateResponseDto referenceCreateResponseDto = referenceService.create(referenceCreateRequestDto);

        // then
        assertThat(referenceCreateResponseDto.getFiles().size(), is(2));
        assertThat(referenceCreateResponseDto.getFiles().get(0).getLink(), is(files.get(0).get("filePath")));
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

    @Test
    public void 게시글_삭제() {
        // given
        Long id = 1L;

        Reference reference = Reference.builder()
                .title("title")
                .content("content")
                .writtenBy(1L)
                .build();



        given(referenceRepository.findById(id)).willReturn(Optional.of(reference));

        // when
        reference.deleteReference();
        ReferenceDeleteResponseDto referenceDeleteResponseDto = reference.toDeleteResponse();

        //then
        assertThat(referenceDeleteResponseDto.getIsDeleted(), is(1L));
    }
}
