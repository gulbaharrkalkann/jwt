package com.gulkalkan.Service.impl;

import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.Service.IAuthService;
import com.gulkalkan.jwt.AuthRequest;
import com.gulkalkan.jwt.AuthResponse;
import com.gulkalkan.jwt.jwtService;
import com.gulkalkan.model.User;
import com.gulkalkan.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements IAuthService {
@Autowired
private UserRepository userRepository;
@Autowired
private BCryptPasswordEncoder passwordEncoder;

@Autowired
private AuthenticationProvider authenticationProvider;

@Autowired
private jwtService jwtService;


    @Override
    public AuthResponse authenticate(AuthRequest request) {

        try {
            UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());


            authenticationProvider.authenticate(auth);
            Optional<User> optionalUser=userRepository.findByUsername(request.getUsername());
            String token =jwtService.generateToken(optionalUser.get());




            return new AuthResponse(token);
        }catch (Exception ex){
            System.out.println("Kullanıcı adı veya şifre hatalı.");
        }
        return null;
    }

    @Override
    public DtoUser register(AuthRequest request) {

        DtoUser dto = new DtoUser();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

       User savedUser= userRepository.save(user);

        BeanUtils.copyProperties(savedUser, dto);
        return dto;
    }


}
