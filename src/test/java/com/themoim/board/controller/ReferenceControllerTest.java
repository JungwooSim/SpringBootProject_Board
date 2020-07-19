package com.themoim.board.controller;

import com.themoim.board.common.ApiResponseTemplate;
import com.themoim.board.dto.*;
import com.themoim.board.service.ReferenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReferenceController.class)
public class ReferenceControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReferenceService referenceService;

    @Test
    public void insert_파일없을때() throws Exception {
        // given
        ReferenceCreateRequestDto referenceCreateRequestDto = ReferenceCreateRequestDto.builder()
                .title("6")
                .content("dddddddd")
                .build();

        List<ReferenceFileContentDto> referenceFileContentDto = new ArrayList<>();
        referenceFileContentDto.add(
                ReferenceFileContentDto
                        .builder()
                        .rflId(1L)
                        .build()
        );
        referenceFileContentDto.add(
                ReferenceFileContentDto
                        .builder()
                        .rflId(2L)
                        .build()
        );

        ReferenceCreateResponseDto referenceCreateResponseDto = ReferenceCreateResponseDto.builder()
                .rId(1L)
                .title(referenceCreateRequestDto.getTitle())
                .content(referenceCreateRequestDto.getContent())
                .build();
        given(referenceService.create(referenceCreateRequestDto)).willReturn(referenceCreateResponseDto);

        // when
        final ResultActions actions = mockMvc.perform(post("/api/reference")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"title\": \"6\",\n" +
                        "    \"content\": \"dddddddd\"\n" +
                        "}")
        ).andDo(print());

        actions.andExpect(status().isCreated());
    }

    @Test
    public void insert() throws Exception {
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

        List<ReferenceFileContentDto> referenceFileContentDto = new ArrayList<>();
        referenceFileContentDto.add(
            ReferenceFileContentDto
                    .builder()
                    .rflId(1L)
                    .link(files.get(0).get("filePath"))
                    .build()
        );
        referenceFileContentDto.add(
                ReferenceFileContentDto
                        .builder()
                        .rflId(2L)
                        .link(files.get(1).get("filePath"))
                        .build()
        );

        ReferenceCreateResponseDto referenceCreateResponseDto = ReferenceCreateResponseDto.builder()
                .rId(1L)
                .title(referenceCreateRequestDto.getTitle())
                .content(referenceCreateRequestDto.getContent())
                .files(referenceFileContentDto)
                .build();
        given(referenceService.create(referenceCreateRequestDto)).willReturn(referenceCreateResponseDto);

        // when
        final ResultActions actions = mockMvc.perform(post("/api/reference")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"title\": \"6\",\n" +
                        "    \"content\": \"dddddddd\",\n" +
                        "    \"files\" : [\n" +
                        "        {\"filePath\" : \"/Users/bigpenguin/file/test1.xls\"},\n" +
                        "        {\"filePath\" : \"/Users/bigpenguin/file/test2.xls\"}\n" +
                        "    ]\n" +
                        "}")
        ).andDo(print());

        actions.andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        Long id = 1L;
        // given
        ReferenceUpdateRequestDto referenceUpdateRequestDto = ReferenceUpdateRequestDto.builder()
                .title("titlechange")
                .content("contentchange")
                .build();

        ReferenceUpdateResponseDto referenceUpdateResponseDto = ReferenceUpdateResponseDto.builder()
                .title("titlechange")
                .content("contentchange")
                .build();

        given(referenceService.update(id, referenceUpdateRequestDto)).willReturn(referenceUpdateResponseDto);

        ApiResponseTemplate<ReferenceUpdateResponseDto> responseResource = ApiResponseTemplate.ok(referenceUpdateResponseDto);

        // when
        final ResultActions actions = mockMvc.perform(patch("/api/reference/"+id)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"titlechange\",\"content\": \"contentchange\"}"))
                .andDo(print());

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)));
    }

    @Test
    public void 게시글_삭제() throws Exception {
        // given
        Long id = 1L;

        given(referenceService.delete(id)).willReturn(null);

        // when
        final ResultActions actions = mockMvc.perform(
                delete("/api/reference/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print());

        //then
        actions.andExpect(status().isOk());
    }
}
