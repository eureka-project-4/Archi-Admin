package com.archiadmin.product.plan.repository;

import com.archiadmin.product.plan.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Page<Plan> findAll(Pageable pageable);

}
