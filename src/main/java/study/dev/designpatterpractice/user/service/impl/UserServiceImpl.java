package study.dev.designpatterpractice.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import study.dev.designpatterpractice.user.constants.UserConstants;
import study.dev.designpatterpractice.user.entity.User;
import study.dev.designpatterpractice.user.mapper.UserMapper;
import study.dev.designpatterpractice.user.repository.UserRepository;
import study.dev.designpatterpractice.user.role.ROLE;
import study.dev.designpatterpractice.user.service.UserService;
import study.dev.designpatterpractice.user.vo.UserDetailsRequestVo;
import study.dev.designpatterpractice.util.exception.UsersAlreadyExistsException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void createUsers(UserDetailsRequestVo userDetailsRequestVo) {

        var checkUserVo = UserDetailsRequestVo.builder()
                .email(userDetailsRequestVo.getEmail())
                .password(passwordEncoder.encode(userDetailsRequestVo.getPassword()))
                .build();

        Optional<User> optionalUsers = userRepository.findByEmail(checkUserVo.getEmail());
        if (optionalUsers.isPresent()) {
            throw new UsersAlreadyExistsException(UserConstants.MESSAGE_ALREADY_EXISTS);
        }

        User user = UserMapper.mapToUsersDetailsRequestVo(checkUserVo);
        User build = user.toBuilder()
                .role(ROLE.USER)
                .build();
        userRepository.save(build);
    }

}
