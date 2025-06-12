package com.archiadmin.vas.dto.response;

import com.archiadmin.vas.dto.VasDto;
import lombok.Data;

import java.util.List;

@Data
public class VasResponseDto {
    private VasDto vasDto;
    private List<VasDto> vasDtoList;

}
