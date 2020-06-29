package com.themoim.board.controller;

import com.themoim.board.common.ApiResponseTemplate;
import com.themoim.board.dto.ReferenceUpdateRequestDto;
import com.themoim.board.dto.ReferenceUpdateResponseDto;
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

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
