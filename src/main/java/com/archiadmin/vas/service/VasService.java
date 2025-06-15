package com.archiadmin.vas.service;


import com.archiadmin.vas.dto.request.VasRequestDto;
import com.archiadmin.vas.dto.response.VasResponseDto;

public interface VasService {
    VasResponseDto registerVas(VasRequestDto vasRequestDto);

    VasResponseDto getVasList(Integer pageNumber, Integer pageSize);
    VasResponseDto getVasById(Long vasId);

    VasResponseDto updateVas(Long vasId, VasRequestDto vasRequestDto);

    void deleteVas(Long vasId);

}
