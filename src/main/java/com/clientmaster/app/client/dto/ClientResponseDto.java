package com.clientmaster.app.client.dto;

import com.clientmaster.app.visit.dto.VisitTimeInfoDto;
import com.clientmaster.app.visit.dto.VisitsOfClientDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientResponseDto {
    private Integer id;
    private String name;
    private String phone;
    private List<VisitsOfClientDto> visits;
}
