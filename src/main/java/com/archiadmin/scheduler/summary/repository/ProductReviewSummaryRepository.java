package com.archiadmin.scheduler.summary.repository;

import com.archiadmin.scheduler.summary.domain.ProductReviewSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReviewSummaryRepository extends JpaRepository<ProductReviewSummary, Long> {

    Optional<ProductReviewSummary> findByProductIdAndReviewType(Long productId, String reviewType);
}

