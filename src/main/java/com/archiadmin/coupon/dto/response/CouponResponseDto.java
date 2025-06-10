package com.archiadmin.coupon.dto.response;

import com.archiadmin.coupon.dto.CouponDto;
import lombok.Data;

import java.util.List;

@Data
public class CouponResponseDto {
    private CouponDto couponDto;
    private List<CouponDto> couponDtoList;

}
