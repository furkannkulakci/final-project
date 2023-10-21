package com.final_project.carRegistrationSystem.api;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.carRegistrationSystem.DTOs.request.CreateUserRequest;
import com.final_project.carRegistrationSystem.DTOs.request.LoginUser;
import com.final_project.carRegistrationSystem.DTOs.response.AuthResponse;
import com.final_project.carRegistrationSystem.bussiness.abstractt.IUserService;
import com.final_project.carRegistrationSystem.entities.User;
import com.final_project.carRegistrationSystem.security.JwtTokenProvider;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthControllers {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final IUserService userService;

    private  BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthControllers(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginUser loginRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user= userService.getByUserName(loginRequest.getUserName()).getData();
        AuthResponse authResponse=new AuthResponse();
        authResponse.setMessage("Bearer "+jwtToken);
        authResponse.setUserId(user.getId());
        authResponse.setUserName(user.getUserName());
        return authResponse;

    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody CreateUserRequest registerRequest) {
        AuthResponse authResponse=new AuthResponse();
        if(userService.getByUserName(registerRequest.getUserName()).getData() != null) {
            authResponse.setMessage("Bu kullanıcı adı alınmış !");
            return authResponse;
        }
        CreateUserRequest user = new CreateUserRequest();
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setUserName(registerRequest.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        userService.createOneUser(user);
        User getUser=userService.getByUserName(user.getUserName()).getData();
        authResponse.setMessage("Kaydınız başarılı şekilde olluşturuldu");
        authResponse.setUserName(getUser.getUserName());
        authResponse.setUserId(getUser.getId());

        return authResponse;


    }





}
