package com.archiadmin.service.dto.response;

import com.archiadmin.service.dto.VasDto;
import lombok.Data;

import java.util.List;

@Data
public class VasResponseDto {
    private VasDto serviceDto;
    private List<VasDto> serviceDtoList;

}
