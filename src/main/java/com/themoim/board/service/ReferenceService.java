package com.themoim.board.service;

import com.themoim.board.domain.Reference;
import com.themoim.board.domain.ReferenceFileLink;
import com.themoim.board.domain.ReferenceFileLinkRepository;
import com.themoim.board.domain.ReferenceRepository;
import com.themoim.board.dto.*;
import com.themoim.board.exception.BusinessErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReferenceService {
    private final ReferenceRepository referenceRepository;
    private final ReferenceFileLinkRepository referenceFileLinkRepository;

    @Transactional
    public ReferenceCreateResponseDto create(ReferenceCreateRequestDto referenceCreateRequestDto) {
        Reference reference = referenceCreateRequestDto.toEntity();

        List<ReferenceFileLink> referenceFileLink = new ArrayList<>();

        referenceCreateRequestDto.getFiles().forEach(
                file -> {
                    referenceFileLink.add(
                            ReferenceFileLink.builder().link(file.get("filePath")).reference(reference).build()
                    );
                }
        );

        referenceRepository.save(reference);
        referenceFileLinkRepository.saveAll(referenceFileLink);

        List<ReferenceFileContentDto> referenceFileContentDto = new ArrayList<>();

        referenceFileLink.forEach(referenceFile -> {
            referenceFileContentDto.add(
                    ReferenceFileContentDto
                            .builder()
                            .rflId(referenceFile.getRflId())
                            .link(referenceFile.getLink())
                            .build()
            );
        });

        ReferenceCreateResponseDto referenceCreateResponseDto = ReferenceCreateResponseDto.builder()
            .rId(reference.getRId())
            .title(reference.getTitle())
            .content(reference.getContent())
            .files(referenceFileContentDto)
            .build();

        return referenceCreateResponseDto;
    }

    public ReferenceListResponseDto list(ReferenceListRequestDto referenceListRequestDto) {
        PageRequest pageRequest = PageRequest.of(
                referenceListRequestDto.getPage(),
                referenceListRequestDto.getSize(),
                Sort.by("CreatedAt").descending()
        );

        Page<Reference> references = referenceRepository.findAll(pageRequest);
        List<ReferenceListContentDto> referenceListContentDto = references.getContent().stream().map(ReferenceListContentDto::new).collect(Collectors.toList());

        ReferenceListResponseDto referenceListResponseDto = ReferenceListResponseDto.builder()
                .page(references.getNumber())
                .size(references.getTotalPages())
                .totalCount(references.getTotalElements())
                .referenceListContentDto(referenceListContentDto)
                .build();

        return referenceListResponseDto;
    }

    @Transactional
    public ReferenceUpdateResponseDto update(Long id, ReferenceUpdateRequestDto referenceUpdateRequestDto) {

        Reference reference = referenceRepository.findById(id).orElseThrow(() -> new BusinessErrorException("유효한 게시물이 아닙니다."));

        reference.changeTitle(referenceUpdateRequestDto.getTitle());
        reference.changeContent(referenceUpdateRequestDto.getContent());
        referenceRepository.save(reference);

        ReferenceUpdateResponseDto referenceUpdateResponseDto = reference.toUpdateResponse();

        return referenceUpdateResponseDto;
    }

    @Transactional
    public ReferenceDeleteResponseDto delete(Long id) {
        Reference reference = referenceRepository.findById(id).orElseThrow(() -> new BusinessErrorException("유효한 게시물이 아닙니다."));
        reference.deleteReference();

        ReferenceDeleteResponseDto referenceDeleteResponseDto = reference.toDeleteResponse();

        return referenceDeleteResponseDto;
    }
}
