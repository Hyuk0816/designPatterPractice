package study.dev.designpatterpractice.user.mapper;


import study.dev.designpatterpractice.user.dto.UserDto;
import study.dev.designpatterpractice.user.entity.User;
import study.dev.designpatterpractice.user.role.ROLE;
import study.dev.designpatterpractice.user.vo.UserDetailsRequestVo;

public class UserMapper {

    public static User mapToUsers(UserDto usersDto) {

        return User.builder()
                .email(usersDto.getEmail())
                .password(usersDto.getPassword())
                .role(ROLE.USER)
                .build();
    }

    public static User mapToUsersDetailsRequestVo(UserDetailsRequestVo userDetailsRequestVo) {
        return User.builder()
                .email(userDetailsRequestVo.getEmail())
                .password(userDetailsRequestVo.getPassword())
                .build();
    }
}

