package com.mopahta.projectmanager.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;
    private String matchingPassword;
}
