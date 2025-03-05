package org.example.userregistration.service;

import org.example.userregistration.util.JwtToken;
import org.example.userregistration.exception.UserException;
import org.example.userregistration.dto.AuthUserDTO;
import org.example.userregistration.dto.LoginDTO;
import org.example.userregistration.model.AuthUser;
import org.example.userregistration.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements  IAuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private JwtToken tokenUtil;

    @Override
    public AuthUser register(AuthUserDTO userDTO) throws Exception {
        AuthUser user = new AuthUser(userDTO);
        System.out.println(user);
        String token = tokenUtil.createToken(user.getUserId());
        authUserRepository.save(user);
        emailSenderService.sendEmail(user.getEmail(),"Welcome to MyHI App", "Hello "
                + user.getFirstName()
                + "\n I am MyHI, your Assistant.\nYou have been successfully registered to MyHI Platform!.\n"
                + "Feel free to ask anything.\n\n\n"
                + "Till then, Here is your Profile and Registration Details:\n\n User Id:  "
                + user.getUserId() + "\n First Name:  "
                + user.getFirstName() + "\n Last Name:  "
                + user.getLastName() + "\n Email:  "
                + user.getEmail() + "\n Address:  "
                + "\n Token:  " + token);
        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) throws UserException {
        Optional<AuthUser> user= Optional.ofNullable(authUserRepository.findByEmail(loginDTO.getEmail()));
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
            emailSenderService.sendEmail(user.get().getEmail(),"Logged in Successfully!", "Hii...."+user.get().getFirstName()+"\n\n You have successfully logged in into Greeting App!");
            return "Congratulations!! You have logged in successfully!";
        } else {
            throw new UserException("Sorry! Email or Password is incorrect!");
        }
    }
}
