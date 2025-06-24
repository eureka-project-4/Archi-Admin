package com.archiadmin.product.vas.repository;

import com.archiadmin.product.vas.entity.Vas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VasRepository extends JpaRepository<Vas, Long> {
    Page<Vas> findAll(Pageable pageable);

}
