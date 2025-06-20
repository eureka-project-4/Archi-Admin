package com.archiadmin.product.coupon.dto.response;

import com.archiadmin.product.coupon.dto.CouponDto;
import lombok.Data;

import java.util.List;

@Data
public class CouponResponseDto {
    private CouponDto couponDto;
    private List<CouponDto> couponDtoList;

}
