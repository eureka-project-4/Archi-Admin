package com.archiadmin.service.service;


import com.archiadmin.service.dto.request.VASRequestDto;
import com.archiadmin.service.dto.response.VASResponseDto;

public interface VASService {
    VASResponseDto registerService(VASRequestDto vasRequestDto);

    VASResponseDto getServiceList(Integer pageNumber, Integer pageSize);
    VASResponseDto getServiceById(Long serviceId);

    VASResponseDto updateService(Long serviceId, VASRequestDto vasRequestDto);

    void deleteService(Long serviceId);

}
