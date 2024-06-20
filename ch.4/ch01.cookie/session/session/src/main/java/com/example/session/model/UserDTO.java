package com.example.session.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String password;
}
