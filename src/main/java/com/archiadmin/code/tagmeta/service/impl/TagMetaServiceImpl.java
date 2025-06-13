package com.archiadmin.code.tagmeta.service.impl;

import com.archiadmin.code.tagmeta.domain.TagMeta;
import com.archiadmin.code.tagmeta.repository.TagMetaRepository;
import com.archiadmin.code.tagmeta.service.TagMetaService;
import lombok.RequiredArgsConstructor;
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
}
