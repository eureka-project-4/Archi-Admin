package com.archiadmin.vas.dto;

import com.archiadmin.vas.entity.Vas;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VasDto {
    private Long vasId;
    private String vasName;
    private int price;
    private String imageUrl;
    private String vasDescription;
    private int saleRate;
    private long tagCode;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static VasDto from(Vas vas) {
        return VasDto.builder()
                .vasId(vas.getVasId())
                .vasName(vas.getVasName())
                .price(vas.getPrice())
                .imageUrl(vas.getImageUrl())
                .vasDescription(vas.getVasDescription())
                .saleRate(vas.getSaleRate())
                .tagCode(vas.getTagCode())
                .categoryCode(vas.getCategoryCode())
                .createdAt(vas.getCreatedAt())
                .updatedAt(vas.getUpdatedAt())
                .build();
    }
}
