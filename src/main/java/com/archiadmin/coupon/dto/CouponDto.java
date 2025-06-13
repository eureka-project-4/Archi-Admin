package com.archiadmin.coupon.dto;

import com.archiadmin.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CouponDto {
    private Long couponId;
    private String couponName;
    private Integer price;
    private List<String> tagList;
    private String imageUrl;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CouponDto from(Coupon coupon, List<String> tagList) {
        return CouponDto.builder()
                .couponId(coupon.getCouponId())
                .couponName(coupon.getCouponName())
                .price(coupon.getPrice())
                .tagList(tagList)
                .imageUrl(coupon.getImageUrl())
                .categoryCode(coupon.getCategoryCode())
                .createdAt(coupon.getCreatedAt())
                .updatedAt(coupon.getUpdatedAt())
                .build();
    }
}
