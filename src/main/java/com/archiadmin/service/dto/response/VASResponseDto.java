package com.archiadmin.service.dto.response;

import com.archiadmin.service.dto.VASDto;
import lombok.Data;

import java.util.List;

@Data
public class VASResponseDto {
    private VASDto serviceDto;
    private List<VASDto> serviceDtoList;

}
