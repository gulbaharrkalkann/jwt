package com.gulkalkan.Service;

import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.jwt.AuthRequest;
import com.gulkalkan.jwt.AuthResponse;

public interface IAuthService {

    public DtoUser register(AuthRequest request);


    public AuthResponse authenticate(AuthRequest request);
}
