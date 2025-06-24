package com.archiadmin.code.tagmeta.service.impl;

import com.archiadmin.code.tagmeta.dto.request.TagMetaDto;
import com.archiadmin.code.tagmeta.dto.response.TagMetaResponseDto;
import com.archiadmin.code.tagmeta.entity.TagMeta;
import com.archiadmin.code.tagmeta.entity.id.TagMetaId;
import com.archiadmin.code.tagmeta.repository.TagMetaRepository;
import com.archiadmin.code.tagmeta.service.TagMetaService;
import com.archiadmin.common.response.CountResponseDto;
import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.exception.business.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagMetaServiceImpl implements TagMetaService {
    private final TagMetaRepository tagMetaRepository;

    @Override
    public List<String> extractTagsFromCode(Long tagCode) {
        if (tagCode == null || tagCode == 0) {
            return Collections.emptyList();
        }

        List<Integer> activeBitPositions = getActiveBitPositions(tagCode);

        if (activeBitPositions.isEmpty()) {
            return Collections.emptyList();
        }

        List<TagMeta> tagMetas = tagMetaRepository.findByBitPositionIn(activeBitPositions);

        return tagMetas.stream()
                .map(TagMeta::getTagDescription)
                .toList();
    }

    private List<Integer> getActiveBitPositions(Long tagCode) {
        List<Integer> positions = new ArrayList<>();

        for (int position = 0; position < 64; position++) {
            if ((tagCode & (1L << position)) != 0) {
                positions.add(position);
            }
        }

        return positions;
    }

    @Override
    public TagMetaResponseDto registerTagMeta(TagMetaDto tagMetaDto) {
        TagMetaResponseDto tagMetaResponseDto = new TagMetaResponseDto();

        TagMetaId tagMetaId = new TagMetaId(tagMetaDto.getTagType(), tagMetaDto.getTagKey());

        if (tagMetaRepository.existsById(tagMetaId)) {
            throw new DuplicateResourceException("이미 존재하는 태그 메타 Id 입니다.");
        }

        TagMeta tagMeta = TagMeta.builder()
                .id(tagMetaId)
                .tagDescription(tagMetaDto.getTagDescription())
                .bitPosition(tagMetaDto.getBitPosition())
                .build();

        TagMeta savedTagMeta = tagMetaRepository.save(tagMeta);

        tagMetaResponseDto.setTagMetaDto(TagMetaDto.from(savedTagMeta));

        return tagMetaResponseDto;
    }

    @Override
    public TagMetaResponseDto getTagMetaList(Integer pageNumber, Integer pageSize) {
        TagMetaResponseDto tagMetaResponseDto = new TagMetaResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<TagMeta> tagMetaPage = tagMetaRepository.findAll(pageable);
        List<TagMeta> tagMetaList = tagMetaPage.toList();

        List<TagMetaDto> tagMetaDtoList = new ArrayList<>();
        for (TagMeta tagMeta : tagMetaList) {
            TagMetaDto dto = TagMetaDto.from(tagMeta);
            tagMetaDtoList.add(dto);
        }

        tagMetaResponseDto.setTagMetaDtoList(tagMetaDtoList);

        return tagMetaResponseDto;
    }

    @Override
    public TagMetaResponseDto getTagMetaById(String tagType, String tagKey) {
        TagMetaResponseDto tagMetaResponseDto = new TagMetaResponseDto();

        TagMetaId tagMetaId = new TagMetaId(tagType, tagKey);

        TagMeta tagMeta = tagMetaRepository.findById(tagMetaId)
                .orElseThrow(() -> new DataNotFoundException("TagMeta Id " + tagMetaId + " Not Found"));

        tagMetaResponseDto.setTagMetaDto(TagMetaDto.from(tagMeta));

        return tagMetaResponseDto;
    }

    @Override
    public TagMetaResponseDto updateTagMeta(TagMetaDto tagMetaDto) {
        TagMetaResponseDto tagMetaResponseDto = new TagMetaResponseDto();

        TagMetaId tagMetaId = new TagMetaId(tagMetaDto.getTagType(), tagMetaDto.getTagKey());

        TagMeta tagMeta = tagMetaRepository.findById(tagMetaId)
                .orElseThrow(() -> new DataNotFoundException("TagMeta Id " + tagMetaId + " Not Found"));

        tagMeta.setTagDescription(tagMetaDto.getTagDescription());
        tagMeta.setBitPosition(tagMetaDto.getBitPosition());

        TagMeta savedTagMeta = tagMetaRepository.save(tagMeta);

        tagMetaResponseDto.setTagMetaDto(TagMetaDto.from(savedTagMeta));

        return tagMetaResponseDto;
    }

    @Override
    public void deleteTagMeta(String tagType, String tagKey) {
        TagMetaResponseDto tagMetaResponseDto = new TagMetaResponseDto();

        TagMetaId tagMetaId = new TagMetaId(tagType, tagKey);

        tagMetaRepository.findById(tagMetaId)
                .orElseThrow(() -> new DataNotFoundException("TagMeta Id " + tagMetaId + " Not Found"));

        tagMetaRepository.deleteById(tagMetaId);
    }

    @Override
    public CountResponseDto countTagMeta() {
        CountResponseDto countResponseDto =  CountResponseDto.builder()
                .count(tagMetaRepository.count())
                .build();

        return countResponseDto;
    }
}
