package com.archiadmin.service.service;

import com.archiadmin.service.dto.request.ServiceRequestDto;
import com.archiadmin.service.dto.response.ServiceResponseDto;

public interface ServiceService {
    ServiceResponseDto registerService(ServiceRequestDto serviceRequestDto);

    ServiceResponseDto getServiceList(Integer pageNumber, Integer pageSize);
    ServiceResponseDto getServiceById(Long serviceId);

    ServiceResponseDto updateService(Long serviceId, ServiceRequestDto serviceRequestDto);

    void deleteService(Long serviceId);

}
