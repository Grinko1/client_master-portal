package com.clientmaster.app.client.entity;

import com.clientmaster.app.user.entity.Profile;
import com.clientmaster.app.user.entity.User;
import com.clientmaster.app.visit.entity.Visit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Запрос на добавление клиента")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Schema(description = "Имя клиента", example = "Jon")
    private String name;
    @Schema(description = "Телефон клиента", example = "89141234567")
    private String phone;
    @OneToMany(mappedBy = "client")
    private List<Visit> visits;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
