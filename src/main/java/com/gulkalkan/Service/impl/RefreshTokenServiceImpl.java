package com.gulkalkan.Service.impl;

import com.gulkalkan.Service.IRefreshTokenService;
import com.gulkalkan.jwt.AuthResponse;
import com.gulkalkan.jwt.RefreshTokenRequest;
import com.gulkalkan.jwt.jwtService;
import com.gulkalkan.model.RefreshToken;
import com.gulkalkan.model.User;
import com.gulkalkan.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private jwtService jwtService;
    public boolean isRefreshTokenExpired(Date expireDate){
        return new Date().before(expireDate);
    }

    public RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
       Optional<RefreshToken> optional =refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
       if (optional.isEmpty()){
           System.out.println("Refresh token geçersiz"+request.getRefreshToken());
       }
       RefreshToken refreshToken = optional.get();

       if (isRefreshTokenExpired(refreshToken.getExpireDate())){
           System.out.println("Refresh token süresi dolmuş"+request.getRefreshToken());
       }
       String accessToken=jwtService.generateToken(refreshToken.getUser());

       RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

       return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());


       }
}
