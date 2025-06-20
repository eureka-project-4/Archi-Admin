package com.archiadmin.product.vas.dto;

import com.archiadmin.product.vas.entity.Vas;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VasDto {
    private Long vasId;
    private String vasName;
    private int price;
    private String imageUrl;
    private String vasDescription;
    private int saleRate;
    private List<String> tagList;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static VasDto from(Vas vas, List<String> tagList) {
        return VasDto.builder()
                .vasId(vas.getVasId())
                .vasName(vas.getVasName())
                .price(vas.getPrice())
                .imageUrl(vas.getImageUrl())
                .vasDescription(vas.getVasDescription())
                .saleRate(vas.getSaleRate())
                .tagList(tagList)
                .categoryCode(vas.getCategoryCode())
                .createdAt(vas.getCreatedAt())
                .updatedAt(vas.getUpdatedAt())
                .build();
    }
}
