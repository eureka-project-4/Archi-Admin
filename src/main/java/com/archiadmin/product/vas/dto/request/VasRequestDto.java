package com.archiadmin.product.vas.dto.request;

import lombok.Getter;

@Getter
public class VasRequestDto {
    private String vasName;
    private int price;
    private String imageUrl;
    private String vasDescription;
    private int saleRate;
    private long tagCode;
    private String categoryCode;
}
