package com.gulkalkan.Controller.impl;

import com.gulkalkan.Controller.IRestAuthController;
import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.Service.IAuthService;
import com.gulkalkan.jwt.AuthRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }
}
