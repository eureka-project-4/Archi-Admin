package com.archiadmin.service.dto.response;

import com.archiadmin.service.dto.ServiceDto;
import lombok.Data;

import java.util.List;

@Data
public class ServiceResponseDto {
    private ServiceDto serviceDto;
    private List<ServiceDto> serviceDtoList;

}
