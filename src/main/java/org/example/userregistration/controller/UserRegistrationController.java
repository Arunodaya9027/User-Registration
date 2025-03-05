package org.example.userregistration.controller;

import jakarta.validation.Valid;
import org.example.userregistration.dto.AuthUserDTO;
import org.example.userregistration.dto.LoginDTO;
import org.example.userregistration.dto.ResponseDTO;
import org.example.userregistration.exception.UserException;
import org.example.userregistration.model.AuthUser;
import org.example.userregistration.service.EmailSenderService;
import org.example.userregistration.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserRegistrationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody AuthUserDTO userDTO) throws Exception{
        AuthUser user=authenticationService.register(userDTO);
        ResponseDTO responseUserDTO =new ResponseDTO("User details is submitted!", user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) throws UserException {
        System.out.println(10);
        String result = authenticationService.login(loginDTO);
        ResponseDTO responseUserDTO=new ResponseDTO("Login successfully!!", result);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
}
