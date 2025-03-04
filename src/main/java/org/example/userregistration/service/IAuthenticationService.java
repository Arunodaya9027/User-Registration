package org.example.userregistration.service;

import org.example.userregistration.dto.AuthUserDTO;
import org.example.userregistration.dto.LoginDTO;
import org.example.userregistration.model.AuthUser;

public interface IAuthenticationService {
    AuthUser register(AuthUserDTO userDTO);
    String login(LoginDTO loginDTO);
}
