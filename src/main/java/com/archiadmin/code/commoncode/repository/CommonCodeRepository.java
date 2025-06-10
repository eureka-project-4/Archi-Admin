package com.archiadmin.code.commoncode.repository;

import com.archiadmin.code.commoncode.entity.CommonCode;
import com.archiadmin.code.commoncode.entity.id.CommonCodeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCode, CommonCodeId> {
    Page<CommonCode> findAll(Pageable pageable);
}
