package study.dev.designpatterpractice.user.service;


import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import study.dev.designpatterpractice.user.entity.User;
import study.dev.designpatterpractice.user.vo.UserDetailsRequestVo;

public interface UserService {

    void createUsers(UserDetailsRequestVo userDetailsRequestVo);



}
