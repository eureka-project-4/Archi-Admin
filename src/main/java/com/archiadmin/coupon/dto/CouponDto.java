package com.archiadmin.coupon.dto;

import com.archiadmin.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CouponDto {
    private Long couponId;
    private String couponName;
    private Integer price;
    private long tagCode;
    private String imageUrl;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CouponDto from(Coupon coupon) {
        return CouponDto.builder()
                .couponId(coupon.getCouponId())
                .couponName(coupon.getCouponName())
                .price(coupon.getPrice())
                .tagCode(coupon.getTagCode())
                .imageUrl(coupon.getImageUrl())
                .categoryCode(coupon.getCategoryCode())
                .createdAt(coupon.getCreatedAt())
                .updatedAt(coupon.getUpdatedAt())
                .build();
    }
}
