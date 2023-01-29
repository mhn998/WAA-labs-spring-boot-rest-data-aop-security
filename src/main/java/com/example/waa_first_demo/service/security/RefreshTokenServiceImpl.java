package com.example.waa_first_demo.service.security;

import com.example.waa_first_demo.domain.RefreshToken;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.domain.dao.UserEntity;
import com.example.waa_first_demo.domain.security.UserDetailsCustom;
import com.example.waa_first_demo.exceptions.TokenRefreshException;
import com.example.waa_first_demo.repo.security.RefreshTokenRepo;
import com.example.waa_first_demo.repo.user.RDBMSCrudSpringUserRepoImp;
import com.example.waa_first_demo.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
//    @Value("${security.app.refreshTokenExpiration ?:120000}")
    private final long refreshTokenDurationMs = 120000;

    private RefreshTokenRepo refreshTokenRepo;

    private RDBMSCrudSpringUserRepoImp userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByRefreshToken(token);
    }


    @Transactional
    public RefreshToken createRefreshToken(UserDetailsCustom userDetails) {
        RefreshToken refreshToken = new RefreshToken();

        Optional<UserEntity> user = userRepository.findById(userDetails.getId());

        refreshToken.setUser(user.get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setRefreshToken(JwtUtil.generateRefreshToken(userDetails.getEmail()));

        refreshToken = refreshTokenRepo.save(refreshToken);

        return refreshToken;
    }


    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new TokenRefreshException(token.getRefreshToken(), "Refresh token was expired. Please make a new signing request");
        }

        return token;
    }


    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepo.deleteById(userId);
    }
}