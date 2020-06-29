package com.themoim.board.controller;

import com.themoim.board.common.ApiResponseTemplate;
import com.themoim.board.dto.*;
import com.themoim.board.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ReferenceController {
    private final ReferenceService referenceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/reference")
    public ApiResponseTemplate<ReferenceCreateResponseDto> create(@Valid @RequestBody ReferenceCreateRequestDto referenceCreateRequestDto) {
        ReferenceCreateResponseDto referenceCreateResponseDto = referenceService.create(referenceCreateRequestDto);
        ApiResponseTemplate<ReferenceCreateResponseDto> responseResource = ApiResponseTemplate.ok(referenceCreateResponseDto);

        return responseResource;
    }

    @PatchMapping("/api/reference/{id}")
    public ApiResponseTemplate<ReferenceUpdateResponseDto> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ReferenceUpdateRequestDto referenceUpdateRequestDto
    ) {
        ReferenceUpdateResponseDto referenceUpdateResponseDto = referenceService.update(id, referenceUpdateRequestDto);
        ApiResponseTemplate<ReferenceUpdateResponseDto> responseResource = ApiResponseTemplate.ok(referenceUpdateResponseDto);
        return responseResource;
    }

    @DeleteMapping("/api/reference/{id}")
    public ApiResponseTemplate<ReferenceDeleteResponseDto> delete(
            @PathVariable("id") Long id
    ) {
        ReferenceDeleteResponseDto referenceDeleteResponseDto = referenceService.delete(id);
        ApiResponseTemplate<ReferenceDeleteResponseDto> responseResource = ApiResponseTemplate.ok(referenceDeleteResponseDto);
        return responseResource;
    }


    @GetMapping("/api/reference/{id}")
    public String referenceDetail() {
        return null;
    }

    @GetMapping("/api/references")
    public ApiResponseTemplate list(@RequestBody ReferenceListRequestDto referenceListRequestDto) {
        ReferenceListResponseDto referenceListResponseDto = referenceService.list(referenceListRequestDto);
        ApiResponseTemplate<ReferenceListResponseDto> responseResource = ApiResponseTemplate.ok(referenceListResponseDto);
        return responseResource;
    }
}
