package com.themoim.board.service;

import com.themoim.board.domain.Reference;
import com.themoim.board.domain.ReferenceRepository;
import com.themoim.board.domain.common.ApiResponseTemplate;
import com.themoim.board.domain.dto.ReferenceCreateRequestDto;
import com.themoim.board.domain.dto.ReferenceListContentDto;
import com.themoim.board.domain.dto.ReferenceListRequestDto;
import com.themoim.board.domain.dto.ReferenceListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReferenceService {
    private final ReferenceRepository referenceRepository;

    public ApiResponseTemplate create(ReferenceCreateRequestDto referenceCreateRequestDto) {
        Reference reference = referenceCreateRequestDto.toEntity();

        referenceRepository.save(reference);

        return ApiResponseTemplate.OK();
    }

    public ApiResponseTemplate list(ReferenceListRequestDto referenceListRequestDto) {
        PageRequest pageRequest = PageRequest.of(
                referenceListRequestDto.getPage(),
                referenceListRequestDto.getSize(),
                Sort.by("CreatedAt").ascending()
        );

        Page<Reference> references = referenceRepository.findAll(pageRequest);
        List<ReferenceListContentDto> referenceListContentDto = references.getContent().stream().map(ReferenceListContentDto::new).collect(Collectors.toList());

        ReferenceListResponseDto referenceListResponseDto = ReferenceListResponseDto.builder()
                .page(references.getNumber())
                .size(references.getTotalPages())
                .totalCount(references.getTotalElements())
                .referenceListContentDto(referenceListContentDto)
                .build();

        return ApiResponseTemplate.OK(referenceListResponseDto);
    }
}
