package com.archiadmin.vas.service.impl;

import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.vas.dto.VasDto;
import com.archiadmin.vas.dto.request.VasRequestDto;
import com.archiadmin.vas.dto.response.VasResponseDto;
import com.archiadmin.vas.entity.Vas;
import com.archiadmin.vas.repository.VasRepository;
import com.archiadmin.vas.service.VasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VasServiceImpl implements VasService {

    private final VasRepository VasRepository;

    @Override
    public VasResponseDto registerVas(VasRequestDto vasRequestDto) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Vas vas = Vas.builder()
                .vasName(vasRequestDto.getVasName())
                .price(vasRequestDto.getPrice())
                .imageUrl(vasRequestDto.getImageUrl())
                .vasDescription(vasRequestDto.getVasDescription())
                .saleRate(vasRequestDto.getSaleRate())
                .tagCode(vasRequestDto.getTagCode())
                .categoryCode(vasRequestDto.getCategoryCode())
                .build();

        Vas savedVas = VasRepository.save(vas);

        vasResponseDto.setVasDto(VasDto.from(savedVas));

        return vasResponseDto;
    }

    @Override
    public VasResponseDto getVasList(Integer pageNumber, Integer pageSize) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Vas> vasPage = VasRepository.findAll(pageable);
        List<Vas> vasList = vasPage.toList();

        List<VasDto> vasDtoList = new ArrayList<>();
        for (Vas vas : vasList) {
            VasDto vasDto = VasDto.from(vas);
            vasDtoList.add(vasDto);
        }

        vasResponseDto.setVasDtoList(vasDtoList);

        return vasResponseDto;
    }

    @Override
    public VasResponseDto getVasById(Long vasId) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Vas vas = VasRepository.findById(vasId)
                .orElseThrow(() -> new DataNotFoundException("Vas Id " + vasId + " Not Found"));

        vasResponseDto.setVasDto(VasDto.from(vas));

        return vasResponseDto;
    }

    @Override
    public VasResponseDto updateVas(Long vasId, VasRequestDto vasRequestDto) {
        VasResponseDto vasResponseDto = new VasResponseDto();

        Vas vas = VasRepository.findById(vasId)
                .orElseThrow(() -> new DataNotFoundException("Vas Id " + vasId + " Not Found"));

        vas.setVasName(vasRequestDto.getVasName());
        vas.setPrice(vasRequestDto.getPrice());
        vas.setImageUrl(vasRequestDto.getImageUrl());
        vas.setVasDescription(vasRequestDto.getVasDescription());
        vas.setSaleRate(vasRequestDto.getSaleRate());
        vas.setTagCode(vasRequestDto.getTagCode());
        vas.setCategoryCode(vasRequestDto.getCategoryCode());

        Vas updatedVas = VasRepository.save(vas);

        vasResponseDto.setVasDto(VasDto.from(updatedVas));

        return vasResponseDto;
    }

    @Override
    public void deleteVas(Long vasId) {
        VasRepository.findById(vasId)
                .orElseThrow(() -> new DataNotFoundException("Vas Id " + vasId + " Not Found"));

        VasRepository.deleteById(vasId);
    }
}
