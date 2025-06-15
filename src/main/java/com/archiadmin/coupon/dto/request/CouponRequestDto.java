package com.archiadmin.coupon.dto.request;

import lombok.Getter;

@Getter
public class CouponRequestDto {
    private String couponName;
    private Integer price;
    private long tagCode;
    private String imageUrl;
    private String categoryCode;
}
