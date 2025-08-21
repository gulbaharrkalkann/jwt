package com.gulkalkan.Service.impl;

import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.Service.IAuthService;
import com.gulkalkan.jwt.AuthRequest;
import com.gulkalkan.model.User;
import com.gulkalkan.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements IAuthService {
@Autowired
private UserRepository userRepository;
@Autowired
private BCryptPasswordEncoder passwordEncoder;


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
