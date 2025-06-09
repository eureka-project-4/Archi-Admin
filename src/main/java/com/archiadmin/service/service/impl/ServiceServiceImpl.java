package com.archiadmin.service.service.impl;

import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.service.dto.ServiceDto;
import com.archiadmin.service.dto.request.ServiceRequestDto;
import com.archiadmin.service.dto.response.ServiceResponseDto;
import com.archiadmin.service.entity.Service;
import com.archiadmin.service.repository.ServiceRepository;
import com.archiadmin.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Override
    public ServiceResponseDto registerService(ServiceRequestDto serviceRequestDto) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

        Service service = Service.builder()
                .serviceName(serviceRequestDto.getServiceName())
                .price(serviceRequestDto.getPrice())
                .imageUrl(serviceRequestDto.getImageUrl())
                .serviceDescription(serviceRequestDto.getServiceDescription())
                .saleRate(serviceRequestDto.getSaleRate())
                .tagCode(serviceRequestDto.getTagCode())
                .categoryCode(serviceRequestDto.getCategoryCode())
                .build();

        Service savedService = serviceRepository.save(service);

        serviceResponseDto.setServiceDto(ServiceDto.from(savedService));

        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto getServiceList(Integer pageNumber, Integer pageSize) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Service> servicePage = serviceRepository.findAll(pageable);
        List<Service> serviceList = servicePage.toList();

        List<ServiceDto> serviceDtoList = new ArrayList<>();
        for (Service service : serviceList) {
            ServiceDto serviceDto = ServiceDto.from(service);
            serviceDtoList.add(serviceDto);
        }

        serviceResponseDto.setServiceDtoList(serviceDtoList);

        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto getServiceById(Long serviceId) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        serviceResponseDto.setServiceDto(ServiceDto.from(service));

        return serviceResponseDto;
    }

    @Override
    public ServiceResponseDto updateService(Long serviceId, ServiceRequestDto serviceRequestDto) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        service.setServiceName(serviceRequestDto.getServiceName());
        service.setPrice(serviceRequestDto.getPrice());
        service.setImageUrl(serviceRequestDto.getImageUrl());
        service.setServiceDescription(serviceRequestDto.getServiceDescription());
        service.setSaleRate(serviceRequestDto.getSaleRate());
        service.setTagCode(serviceRequestDto.getTagCode());
        service.setCategoryCode(serviceRequestDto.getCategoryCode());

        Service updatedService = serviceRepository.save(service);

        serviceResponseDto.setServiceDto(ServiceDto.from(updatedService));

        return serviceResponseDto;
    }

    @Override
    public void deleteService(Long serviceId) {
        serviceRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        serviceRepository.deleteById(serviceId);
    }
}
