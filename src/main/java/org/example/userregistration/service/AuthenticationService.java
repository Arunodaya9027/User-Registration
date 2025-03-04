package org.example.userregistration.service;

import jdk.jshell.spi.ExecutionControl;
import org.example.userregistration.dto.AuthUserDTO;
import org.example.userregistration.dto.LoginDTO;
import org.example.userregistration.model.AuthUser;
import org.example.userregistration.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public AuthUser register(AuthUserDTO userDTO) throws Exception {
        AuthUser user=new AuthUser(userDTO);
        System.out.println(user);
        String token=tokenUtil.createToken(user.getUserId());
        authUserRepository.save(user);
        emailSenderService.sendEmail(user.getEmail(),"Registered in Greeting App", "Hii...."
                +user.getFirstName()+"\n You have been successfully registered!\n\n Your registered details are:\n\n User Id:  "
                +user.getUserId()+"\n First Name:  "
                +user.getFirstName()+"\n Last Name:  "
                +user.getLastName()+"\n Email:  "
                +user.getEmail()+"\n Address:  "
                +"\n Token:  " +token);
        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Optional<AuthUser> user= Optional.ofNullable(authUserRepository.findByEmail(loginDTO.getEmail()));
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword()) ){
            emailSenderService.sendEmail(user.get().getEmail(),"Logged in Successfully!", "Hii...."+user.get().getFirstName()+"\n\n You have successfully logged in into Greeting App!");
            return "Congratulations!! You have logged in successfully!";
        }else {
            throw new ExecutionControl.UserException("Sorry! Email or Password is incorrect!");
        }
    }
}
