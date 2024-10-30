package study.dev.designpatterpractice.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import study.dev.designpatterpractice.user.dto.LoginDto;
import study.dev.designpatterpractice.user.vo.response.AccessTokenResponse;
import study.dev.designpatterpractice.user.vo.response.UserInfoKakaoTokenVo;


public interface AuthService {
    void login(LoginDto loginDto, HttpServletResponse response) throws BadRequestException;
    void logout(HttpServletRequest request, HttpServletResponse response);
    AccessTokenResponse getAccessToken(HttpServletRequest request, HttpServletResponse response);

    void checkAuthentication(Authentication authentication);
}
