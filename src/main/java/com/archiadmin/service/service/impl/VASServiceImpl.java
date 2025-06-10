package com.archiadmin.service.service.impl;

import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.service.dto.VASDto;
import com.archiadmin.service.dto.request.VASRequestDto;
import com.archiadmin.service.dto.response.VASResponseDto;
import com.archiadmin.service.entity.VAS;
import com.archiadmin.service.repository.VASRepository;
import com.archiadmin.service.service.VASService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VASServiceImpl implements VASService {

    private final VASRepository VASRepository;

    @Override
    public VASResponseDto registerService(VASRequestDto vasRequestDto) {
        VASResponseDto vasResponseDto = new VASResponseDto();

        VAS vas = VAS.builder()
                .serviceName(vasRequestDto.getServiceName())
                .price(vasRequestDto.getPrice())
                .imageUrl(vasRequestDto.getImageUrl())
                .serviceDescription(vasRequestDto.getServiceDescription())
                .saleRate(vasRequestDto.getSaleRate())
                .tagCode(vasRequestDto.getTagCode())
                .categoryCode(vasRequestDto.getCategoryCode())
                .build();

        VAS savedVAS = VASRepository.save(vas);

        vasResponseDto.setServiceDto(VASDto.from(savedVAS));

        return vasResponseDto;
    }

    @Override
    public VASResponseDto getServiceList(Integer pageNumber, Integer pageSize) {
        VASResponseDto vasResponseDto = new VASResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VAS> vasPage = VASRepository.findAll(pageable);
        List<VAS> vasList = vasPage.toList();

        List<VASDto> vasDtoList = new ArrayList<>();
        for (VAS vas : vasList) {
            VASDto vasDto = VASDto.from(vas);
            vasDtoList.add(vasDto);
        }

        vasResponseDto.setServiceDtoList(vasDtoList);

        return vasResponseDto;
    }

    @Override
    public VASResponseDto getServiceById(Long serviceId) {
        VASResponseDto vasResponseDto = new VASResponseDto();

        VAS vas = VASRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        vasResponseDto.setServiceDto(VASDto.from(vas));

        return vasResponseDto;
    }

    @Override
    public VASResponseDto updateService(Long serviceId, VASRequestDto vasRequestDto) {
        VASResponseDto vasResponseDto = new VASResponseDto();

        VAS vas = VASRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        vas.setServiceName(vasRequestDto.getServiceName());
        vas.setPrice(vasRequestDto.getPrice());
        vas.setImageUrl(vasRequestDto.getImageUrl());
        vas.setServiceDescription(vasRequestDto.getServiceDescription());
        vas.setSaleRate(vasRequestDto.getSaleRate());
        vas.setTagCode(vasRequestDto.getTagCode());
        vas.setCategoryCode(vasRequestDto.getCategoryCode());

        VAS updatedService = VASRepository.save(vas);

        vasResponseDto.setServiceDto(VASDto.from(updatedService));

        return vasResponseDto;
    }

    @Override
    public void deleteService(Long serviceId) {
        VASRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        VASRepository.deleteById(serviceId);
    }
}
