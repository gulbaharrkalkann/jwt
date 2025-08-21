package com.gulkalkan.Controller;

import com.gulkalkan.Dto.DtoUser;
import com.gulkalkan.jwt.AuthRequest;

public interface IRestAuthController {

    public DtoUser register(AuthRequest request);
}
