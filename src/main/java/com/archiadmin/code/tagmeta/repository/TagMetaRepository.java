package com.archiadmin.code.tagmeta.repository;

import com.archiadmin.code.tagmeta.entity.TagMeta;
import com.archiadmin.code.tagmeta.entity.id.TagMetaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagMetaRepository extends JpaRepository<TagMeta, TagMetaId> {
    List<TagMeta> findByBitPositionIn(List<Integer> bitPositions);

    Page<TagMeta> findAll(Pageable pageable);
}
