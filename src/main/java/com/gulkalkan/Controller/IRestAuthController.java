package com.gulkalkan.Controller;

import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.jwt.AuthRequest;
import com.gulkalkan.jwt.AuthResponse;
import com.gulkalkan.jwt.RefreshTokenRequest;

public interface IRestAuthController {

    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);

    public AuthResponse refreshToken(RefreshTokenRequest request);

}
