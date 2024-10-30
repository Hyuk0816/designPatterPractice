package study.dev.designpatterpractice.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.dev.designpatterpractice.user.constants.UserConstants;
import study.dev.designpatterpractice.user.service.UserService;
import study.dev.designpatterpractice.user.vo.UserDetailsRequestVo;
import study.dev.designpatterpractice.user.vo.UserStatusResponseVo;
import study.dev.designpatterpractice.util.error.ErrorResponseDto;

@Tag(
        name = "CRUD REST APIs for Users",
        description = "CRUD REST APIs in Users to CREATE, UPDATE, FETCH AND DELETE"
)
@RestController
@RequestMapping(path = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "REST API to create new User"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(path = "/registration")
    public ResponseEntity<UserStatusResponseVo> createUsers(@Valid @RequestBody UserDetailsRequestVo usersDetailsRequestVo) {
        userService.createUsers(usersDetailsRequestVo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserStatusResponseVo(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }
   

}
