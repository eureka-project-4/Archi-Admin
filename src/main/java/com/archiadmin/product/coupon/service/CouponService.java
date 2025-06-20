package com.archiadmin.product.coupon.service;

import com.archiadmin.product.coupon.dto.request.CouponRequestDto;
import com.archiadmin.product.coupon.dto.response.CouponResponseDto;

public interface CouponService {
    CouponResponseDto registerCoupon(CouponRequestDto couponRequestDto);

    CouponResponseDto getCouponList(Integer pageNumber, Integer pageSize);
    CouponResponseDto getCouponById(Long couponId);

    CouponResponseDto updateCoupon(Long couponId, CouponRequestDto couponRequestDto);

    void deleteCoupon(Long couponId);

}
