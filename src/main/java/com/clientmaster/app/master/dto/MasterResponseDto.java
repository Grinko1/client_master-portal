package com.clientmaster.app.master.dto;

import com.clientmaster.app.visit.dto.VisitResponseDto;
import com.clientmaster.app.visit.dto.VisitTimeInfoDto;
import com.clientmaster.app.visit.dto.VisitToMasterDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MasterResponseDto {
    private Integer id;
    private String name;
    private String description;
    private List<VisitToMasterDto> visits;
}
