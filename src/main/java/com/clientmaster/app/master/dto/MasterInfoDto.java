package com.clientmaster.app.master.dto;

import com.clientmaster.app.user.entity.Profile;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MasterInfoDto implements Profile {
    private Integer id;
    private String name;
    private String description;
    private Long user_id;
}
