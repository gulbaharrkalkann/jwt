package com.gulkalkan.Service;

import com.gulkalkan.jwt.AuthResponse;
import com.gulkalkan.jwt.RefreshTokenRequest;
import com.gulkalkan.model.RefreshToken;

public interface IRefreshTokenService {
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
