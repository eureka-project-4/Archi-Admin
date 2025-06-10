package com.archiadmin.service.service;


import com.archiadmin.service.dto.request.VasRequestDto;
import com.archiadmin.service.dto.response.VasResponseDto;

public interface VasService {
    VasResponseDto registerService(VasRequestDto vasRequestDto);

    VasResponseDto getServiceList(Integer pageNumber, Integer pageSize);
    VasResponseDto getServiceById(Long serviceId);

    VasResponseDto updateService(Long serviceId, VasRequestDto vasRequestDto);

    void deleteService(Long serviceId);

}
