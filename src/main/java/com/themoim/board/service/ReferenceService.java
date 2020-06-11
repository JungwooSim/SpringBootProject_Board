package com.themoim.board.service;

import com.themoim.board.domain.Reference;
import com.themoim.board.domain.ReferenceRepository;
import com.themoim.board.domain.common.ApiResponseTemplate;
import com.themoim.board.domain.dto.ReferenceCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReferenceService {
    private final ReferenceRepository referenceRepository;

    public ApiResponseTemplate create(ReferenceCreateRequestDto referenceCreateRequestDto) {
        Reference reference = referenceCreateRequestDto.toEntity();

        referenceRepository.save(reference);

        return ApiResponseTemplate.OK();
    }
}
