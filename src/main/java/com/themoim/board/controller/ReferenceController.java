package com.themoim.board.controller;

import com.themoim.board.domain.common.ApiResponseTemplate;
import com.themoim.board.domain.dto.ReferenceCreateRequestDto;
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
    public ApiResponseTemplate create(@Valid @RequestBody ReferenceCreateRequestDto referenceCreateRequestDto) {
        return referenceService.create(referenceCreateRequestDto);
    }

    @GetMapping("/api/reference/{id}")
    public String referenceDetail() {
        return null;
    }

    @GetMapping("/api/reference")
    public String list() {
        return null;
    }
}
