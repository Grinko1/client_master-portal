package com.clientmaster.app.client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientInfoDto {
    private Integer id;
    private String name;
    private String phone;
}
