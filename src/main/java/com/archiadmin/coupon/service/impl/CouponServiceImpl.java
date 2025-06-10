package com.archiadmin.coupon.service.impl;

import com.archiadmin.coupon.dto.request.CouponRequestDto;
import com.archiadmin.coupon.dto.response.CouponResponseDto;
import com.archiadmin.coupon.entity.Coupon;
import com.archiadmin.coupon.repository.CouponRepository;
import com.archiadmin.coupon.service.CouponService;
import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.coupon.dto.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public CouponResponseDto registerCoupon(CouponRequestDto couponRequestDto) {
        CouponResponseDto couponResponseDto = new CouponResponseDto();

        Coupon coupon = Coupon.builder()
                .couponName(couponRequestDto.getCouponName())
                .price(couponRequestDto.getPrice())
                .tagCode(couponRequestDto.getTagCode())
                .imageUrl(couponRequestDto.getImageUrl())
                .categoryCode(couponRequestDto.getCategoryCode())
                .build();

        Coupon savedCoupon = couponRepository.save(coupon);

        couponResponseDto.setCouponDto(CouponDto.from(savedCoupon));

        return couponResponseDto;
    }

    @Override
    public CouponResponseDto getCouponList(Integer pageNumber, Integer pageSize) {
        CouponResponseDto couponResponseDto = new CouponResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Coupon> couponPage = couponRepository.findAll(pageable);
        List<Coupon> couponList = couponPage.toList();

        List<CouponDto> couponDtoList = new ArrayList<>();
        for (Coupon coupon : couponList) {
            CouponDto couponDto = CouponDto.from(coupon);
            couponDtoList.add(couponDto);
        }

        couponResponseDto.setCouponDtoList(couponDtoList);

        return couponResponseDto;
    }

    @Override
    public CouponResponseDto getCouponById(Long couponId) {
        CouponResponseDto couponResponseDto = new CouponResponseDto();

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new DataNotFoundException("Coupon Id " + couponId + " Not Found"));

        couponResponseDto.setCouponDto(CouponDto.from(coupon));

        return couponResponseDto;
    }

    @Override
    public CouponResponseDto updateCoupon(Long couponId, CouponRequestDto couponRequestDto) {
        CouponResponseDto couponResponseDto = new CouponResponseDto();

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new DataNotFoundException("Coupon Id " + couponId + " Not Found"));

        coupon.setCouponName(couponRequestDto.getCouponName());
        coupon.setPrice(couponRequestDto.getPrice());
        coupon.setTagCode(couponRequestDto.getTagCode());
        coupon.setImageUrl(couponRequestDto.getImageUrl());
        coupon.setCategoryCode(couponRequestDto.getCategoryCode());

        Coupon updatedCoupon = couponRepository.save(coupon);

        couponResponseDto.setCouponDto(CouponDto.from(updatedCoupon));

        return couponResponseDto;
    }

    @Override
    public void deleteCoupon(Long couponId) {
        couponRepository.findById(couponId)
                .orElseThrow(() -> new DataNotFoundException("Coupon Id " + couponId + " Not Found"));

        couponRepository.deleteById(couponId);
    }
}
