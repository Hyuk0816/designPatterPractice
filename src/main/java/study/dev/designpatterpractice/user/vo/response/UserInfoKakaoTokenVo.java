package study.dev.designpatterpractice.user.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoKakaoTokenVo {

    private String email;
    private String kakaoToken;
    private String profile;
}
