package study.dev.designpatterpractice.user.util;


import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import study.dev.designpatterpractice.user.constants.UserConstants;
import study.dev.designpatterpractice.user.entity.User;
import study.dev.designpatterpractice.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(UserInfo.class) != null &&
                parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(UserConstants.MESSAGE_404));
    }
}
