package com.clientmaster.app.visit.dto;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.master.dto.MasterInfoDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VisitResponseDto {
    private Integer id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date time;
    private ClientInfoDto client;
    private MasterInfoDto master;
}
