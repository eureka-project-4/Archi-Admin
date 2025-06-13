package com.archiadmin.code.tagmeta.repository;

import com.archiadmin.code.tagmeta.domain.TagMeta;
import com.archiadmin.code.tagmeta.domain.id.TagMetaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagMetaRepository extends JpaRepository<TagMeta, TagMetaId> {
    List<TagMeta> findByBitPositionIn(List<Integer> bitPositions);
}
