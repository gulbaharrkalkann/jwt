package com.gulkalkan.Controller.impl;

import com.gulkalkan.Controller.IRestAuthController;
import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.Service.IAuthService;
import com.gulkalkan.Service.IRefreshTokenService;
import com.gulkalkan.jwt.AuthRequest;
import com.gulkalkan.jwt.AuthResponse;
import com.gulkalkan.jwt.RefreshTokenRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.refreshToken(request);
    }
}
