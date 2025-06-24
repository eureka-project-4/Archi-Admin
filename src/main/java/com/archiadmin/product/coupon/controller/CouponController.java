package com.archiadmin.product.coupon.controller;

import com.archiadmin.common.response.ApiResponse;
import com.archiadmin.common.response.CountResponseDto;
import com.archiadmin.product.coupon.dto.request.CouponRequestDto;
import com.archiadmin.product.coupon.dto.response.CouponResponseDto;
import com.archiadmin.product.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<ApiResponse<CouponResponseDto>> registerCoupon(@RequestBody CouponRequestDto couponRequestDto) {
        CouponResponseDto data = couponService.registerCoupon(couponRequestDto);
        return ResponseEntity.ok(ApiResponse.success("라이프 쿠폰 등록이 성공적으로 처리되었습니다.", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CouponResponseDto>> getCouponList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        CouponResponseDto data = couponService.getCouponList(pageNumber, pageSize);
        return ResponseEntity.ok(ApiResponse.success("전체 라이프 쿠폰 조회가 성공적으로 처리되었습니다.", data));
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<ApiResponse<CouponResponseDto>> getCouponById(@PathVariable Long couponId) {
        CouponResponseDto data = couponService.getCouponById(couponId);
        return ResponseEntity.ok(ApiResponse.success("Coupon Id " + couponId + " 조회가 성공적으로 처리되었습니다.", data));
    }

    @PutMapping("/{couponId}")
    public ResponseEntity<ApiResponse<CouponResponseDto>> updateCoupon(@PathVariable Long couponId, @RequestBody CouponRequestDto couponRequestDto) {
        CouponResponseDto data = couponService.updateCoupon(couponId, couponRequestDto);
        return ResponseEntity.ok(ApiResponse.success("Coupon Id " + couponId + " 수정이 성공적으로 처리되었습니다.", data));
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse<Void>> deleteCoupon(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.ok(ApiResponse.success("Coupon Id " + couponId + " 삭제가 성공적으로 처리되었습니다.", null));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<CountResponseDto>> countCoupon() {
        return ResponseEntity.ok(ApiResponse.success(couponService.countCoupon()));
    }
}
