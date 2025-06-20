package com.archiadmin.product.vas.service.impl;

import com.archiadmin.code.tagmeta.service.TagMetaService;
import com.archiadmin.exception.business.DataNotFoundException;
import com.archiadmin.product.vas.dto.VasDto;
import com.archiadmin.product.vas.dto.request.VasRequestDto;
import com.archiadmin.product.vas.dto.response.VasResponseDto;
import com.archiadmin.product.vas.entity.Vas;
import com.archiadmin.product.vas.repository.VasRepository;
import com.archiadmin.product.vas.service.VasService;
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
    private final TagMetaService tagMetaService;

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

        List<String> tagList = tagMetaService.extractTagsFromCode(savedVas.getTagCode());

        vasResponseDto.setVasDto(VasDto.from(savedVas, tagList));

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
            List<String> tagList = tagMetaService.extractTagsFromCode(vas.getTagCode());
            VasDto vasDto = VasDto.from(vas, tagList);
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

        List<String> tagList = tagMetaService.extractTagsFromCode(vas.getTagCode());

        vasResponseDto.setVasDto(VasDto.from(vas, tagList));

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

        List<String> tagList = tagMetaService.extractTagsFromCode(updatedVas.getTagCode());

        vasResponseDto.setVasDto(VasDto.from(updatedVas, tagList));

        return vasResponseDto;
    }

    @Override
    public void deleteVas(Long vasId) {
        VasRepository.findById(vasId)
                .orElseThrow(() -> new DataNotFoundException("Vas Id " + vasId + " Not Found"));

        VasRepository.deleteById(vasId);
    }
}
