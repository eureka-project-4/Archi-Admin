package com.archiadmin.product.coupon.service.impl;

import com.archiadmin.code.tagmeta.service.TagMetaService;
import com.archiadmin.product.coupon.dto.request.CouponRequestDto;
import com.archiadmin.product.coupon.dto.response.CouponResponseDto;
import com.archiadmin.product.coupon.entity.Coupon;
import com.archiadmin.product.coupon.repository.CouponRepository;
import com.archiadmin.product.coupon.service.CouponService;
import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.product.coupon.dto.CouponDto;
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
    private final TagMetaService tagMetaService;

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

        List<String> tagList = tagMetaService.extractTagsFromCode(coupon.getTagCode());

        couponResponseDto.setCouponDto(CouponDto.from(savedCoupon, tagList));

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
            List<String> tagList = tagMetaService.extractTagsFromCode(coupon.getTagCode());
            CouponDto couponDto = CouponDto.from(coupon, tagList);
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

        List<String> tagList = tagMetaService.extractTagsFromCode(coupon.getTagCode());

        couponResponseDto.setCouponDto(CouponDto.from(coupon, tagList));

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

        List<String> tagList = tagMetaService.extractTagsFromCode(coupon.getTagCode());

        couponResponseDto.setCouponDto(CouponDto.from(updatedCoupon, tagList));

        return couponResponseDto;
    }

    @Override
    public void deleteCoupon(Long couponId) {
        couponRepository.findById(couponId)
                .orElseThrow(() -> new DataNotFoundException("Coupon Id " + couponId + " Not Found"));

        couponRepository.deleteById(couponId);
    }
}
