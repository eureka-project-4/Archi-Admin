package com.archiadmin.service.repository;

import com.archiadmin.service.entity.VAS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VASRepository extends JpaRepository<VAS, Long> {
    Page<VAS> findAll(Pageable pageable);

}
