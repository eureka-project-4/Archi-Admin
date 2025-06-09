package com.archiadmin.service.dto;

import com.archiadmin.service.entity.Service;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ServiceDto {
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

    public static ServiceDto from(Service service) {
        return ServiceDto.builder()
                .serviceId(service.getServiceId())
                .serviceName(service.getServiceName())
                .price(service.getPrice())
                .imageUrl(service.getImageUrl())
                .serviceDescription(service.getServiceDescription())
                .saleRate(service.getSaleRate())
                .tagCode(service.getTagCode())
                .categoryCode(service.getCategoryCode())
                .createdAt(service.getCreatedAt())
                .updatedAt(service.getUpdatedAt())
                .build();
    }
}
