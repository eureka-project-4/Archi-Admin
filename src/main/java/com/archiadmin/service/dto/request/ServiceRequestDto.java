package com.archiadmin.service.dto.request;

import lombok.Getter;

@Getter
public class ServiceRequestDto {
    private String serviceName;
    private int price;
    private String imageUrl;
    private String serviceDescription;
    private int saleRate;
    private long tagCode;
    private String categoryCode;
}
