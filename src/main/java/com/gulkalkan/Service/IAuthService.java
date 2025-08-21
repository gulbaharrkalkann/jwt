package com.gulkalkan.Service;

import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.jwt.AuthRequest;

public interface IAuthService {

    public DtoUser register(AuthRequest request);
}
