package study.dev.designpatterpractice.user.userDetails;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.dev.designpatterpractice.user.constants.UserConstants;
import study.dev.designpatterpractice.user.entity.User;
import study.dev.designpatterpractice.user.repository.UserRepository;
import study.dev.designpatterpractice.util.exception.UserNameNotFoundException;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername member ID : " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNameNotFoundException(UserConstants.STATUS_404, UserConstants.MESSAGE_404));
        return new UserDetailsImpl(user);
    }
}
