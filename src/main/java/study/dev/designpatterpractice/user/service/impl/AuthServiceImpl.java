package study.dev.designpatterpractice.user.service.impl;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.designpatterpractice.user.constants.AuthConstants;
import study.dev.designpatterpractice.user.constants.UserConstants;
import study.dev.designpatterpractice.user.dto.LoginDto;
import study.dev.designpatterpractice.user.repository.UserRepository;
import study.dev.designpatterpractice.user.service.AuthService;
import study.dev.designpatterpractice.user.userDetails.CustomUserDetails;
import study.dev.designpatterpractice.user.vo.response.AccessTokenResponse;
import study.dev.designpatterpractice.util.jwt.TokenProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private static final long maxAgeForCookie = 7 * 24 * 60 * 60;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository usersRepository;
    private final CustomUserDetails customUserDetails;


    @Override
    @Transactional
    public void login(LoginDto loginDto, HttpServletResponse response) {

        usersRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(AuthConstants.MESSAGE_404));

        Authentication authentication;
        try{
            authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = tokenProvider.createAccessToken(authentication);
            String refreshToken = tokenProvider.createRefreshToken(authentication);

            response.addHeader("Authorization", "Bearer " + accessToken);

            tokenProvider.createRefreshTokenCookie(response, "refreshToken", refreshToken, maxAgeForCookie);

        }catch(BadCredentialsException e){
            throw new BadCredentialsException(AuthConstants.MESSAGE_401);
        }
    }



    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<String> cookieValue = Arrays.stream(cookies)
                    .filter(c -> "refreshToken".equals(c.getName()) && hasText(c.getValue()))
                    .map(Cookie::getValue)
                    .findFirst();

            cookieValue.ifPresent(value -> {
                tokenProvider.deleteRefreshTokenCookie(response, "refreshToken");
                // refreshToken delete
                redisTemplate.delete(value);
            });
        }
        // accessToken delete
//        redisTemplate.delete(user.getEmail());
    }


    // acess token redis에 있는지 확인 필요 email check 필요
    // 로그아웃 상태일때 헤더에는 토큰이 없으므로 UsernameNotFoundExceptiond에 걸림
    // Authentication 클래스는 헤더에서 AccessToken를 가져옴
    @Override
    @Transactional
    public AccessTokenResponse getAccessToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<String> cookieValue = Arrays.stream(cookies)
                    .filter(c -> "refreshToken".equals(c.getName()) && hasText(c.getValue()))
                    .map(Cookie::getValue)
                    .findFirst();

            if (cookieValue.isPresent()) {
                log.info(cookieValue.get());
                String accessToken = tokenProvider.searchAccessTokenByRefreshToken(cookieValue.get());
                return AccessTokenResponse.builder()
                        .accessToken(accessToken)
                        .type("bearer")
                        .build();
            }
        }
        return null;
    }


    @Override
    public void checkAuthentication(Authentication authentication) {
        if(authentication == null){
            throw new UsernameNotFoundException(UserConstants.REQUIRED_LOGIN);
        }
    }

}
