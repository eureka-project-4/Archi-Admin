package com.archiadmin.service.dto;

import com.archiadmin.service.entity.Vas;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VasDto {
    private Long serviceId;
    private String serviceName;
    private int price;
    private String imageUrl;
    private String serviceDescription;
    private int saleRate;
    private long tagCode;
    private String categoryCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static VasDto from(Vas vas) {
        return VasDto.builder()
                .serviceId(vas.getServiceId())
                .serviceName(vas.getServiceName())
                .price(vas.getPrice())
                .imageUrl(vas.getImageUrl())
                .serviceDescription(vas.getServiceDescription())
                .saleRate(vas.getSaleRate())
                .tagCode(vas.getTagCode())
                .categoryCode(vas.getCategoryCode())
                .createdAt(vas.getCreatedAt())
                .updatedAt(vas.getUpdatedAt())
                .build();
    }
}
