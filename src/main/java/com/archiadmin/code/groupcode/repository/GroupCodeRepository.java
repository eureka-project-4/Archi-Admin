package com.archiadmin.code.groupcode.repository;

import com.archiadmin.code.groupcode.entity.GroupCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupCodeRepository extends JpaRepository<GroupCode, String> {
    Page<GroupCode> findAll(Pageable pageable);
}
