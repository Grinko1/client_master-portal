package com.clientmaster.app.client.dto;

import com.clientmaster.app.user.entity.Profile;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientInfoDto implements Profile {
    private Integer id;
    private String name;
    private String phone;
}
