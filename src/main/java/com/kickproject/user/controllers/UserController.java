package com.kickproject.user.controllers;

import com.kickproject.user.dto.MainResponse;
import com.kickproject.user.dto.UserDto;
import com.kickproject.user.entities.User;
import com.kickproject.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/user")
@Tag(name = "User", description = "UserController")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    @Operation(summary = "Получить данные пользователя", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity getUser(@RequestHeader(value = "user-phone") String userPhone,
                                        @RequestHeader(value = "user-id") Long userId) {
        UserDto userDto = userService.getUser(userPhone, userId);
        return new ResponseEntity<>(new MainResponse<UserDto>().result(userDto), HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody UserDto userDtoReq) {
        UserDto userDtoResp = userService.saveUser(userDtoReq);
        return new ResponseEntity<>(new MainResponse<UserDto>().result(userDtoResp), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestHeader(value = "user-phone") String userPhone,
                                     @RequestHeader(value = "user-id") Long userId) {
        userService.deleteUser(userPhone, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/editUser")
    public ResponseEntity editUser(@RequestHeader(value = "user-phone") String userPhone,
                                   @RequestHeader(value = "user-id") Long userId, @RequestBody UserDto userDtoReq) {
        UserDto userDtoResp = userService.editUser(userPhone, userId, userDtoReq);
        return new ResponseEntity<>(new MainResponse<UserDto>().result(userDtoResp), HttpStatus.OK);
    }

}
