package com.mopahta.projectmanager.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String matchingPassword;

    private String roles;
}
