package com.archiadmin.service.service.impl;

import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.service.dto.VasDto;
import com.archiadmin.service.dto.request.VasRequestDto;
import com.archiadmin.service.dto.response.VasResponseDto;
import com.archiadmin.service.entity.Vas;
import com.archiadmin.service.repository.VasRepository;
import com.archiadmin.service.service.VasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VasServiceImpl implements VasService {

    private final VasRepository VasRepository;

    @Override
    public VasResponseDto registerService(VasRequestDto vasRequestDto) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Vas vas = Vas.builder()
                .serviceName(vasRequestDto.getServiceName())
                .price(vasRequestDto.getPrice())
                .imageUrl(vasRequestDto.getImageUrl())
                .serviceDescription(vasRequestDto.getServiceDescription())
                .saleRate(vasRequestDto.getSaleRate())
                .tagCode(vasRequestDto.getTagCode())
                .categoryCode(vasRequestDto.getCategoryCode())
                .build();

        Vas savedVas = VasRepository.save(vas);

        vasResponseDto.setServiceDto(VasDto.from(savedVas));

        return vasResponseDto;
    }

    @Override
    public VasResponseDto getServiceList(Integer pageNumber, Integer pageSize) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Vas> vasPage = VasRepository.findAll(pageable);
        List<Vas> vasList = vasPage.toList();

        List<VasDto> vasDtoList = new ArrayList<>();
        for (Vas vas : vasList) {
            VasDto vasDto = VasDto.from(vas);
            vasDtoList.add(vasDto);
        }

        vasResponseDto.setServiceDtoList(vasDtoList);

        return vasResponseDto;
    }

    @Override
    public VasResponseDto getServiceById(Long serviceId) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Vas vas = VasRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        vasResponseDto.setServiceDto(VasDto.from(vas));

        return vasResponseDto;
    }

    @Override
    public VasResponseDto updateService(Long serviceId, VasRequestDto vasRequestDto) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Vas vas = VasRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        vas.setServiceName(vasRequestDto.getServiceName());
        vas.setPrice(vasRequestDto.getPrice());
        vas.setImageUrl(vasRequestDto.getImageUrl());
        vas.setServiceDescription(vasRequestDto.getServiceDescription());
        vas.setSaleRate(vasRequestDto.getSaleRate());
        vas.setTagCode(vasRequestDto.getTagCode());
        vas.setCategoryCode(vasRequestDto.getCategoryCode());

        Vas updatedService = VasRepository.save(vas);

        vasResponseDto.setServiceDto(VasDto.from(updatedService));

        return vasResponseDto;
    }

    @Override
    public void deleteService(Long serviceId) {
        VasRepository.findById(serviceId)
                .orElseThrow(() -> new DataNotFoundException("Service Id " + serviceId + " Not Found"));

        VasRepository.deleteById(serviceId);
    }
}
