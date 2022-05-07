package com.dhaudgkr.jwtsample.domain.user.dto;

import com.dhaudgkr.jwtsample.domain.user.entity.User;
import com.dhaudgkr.jwtsample.global.config.common.RegisterEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRegisterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String username;
        private String password;

        @Builder
        public Request (String username, String password) {
            this.username = username;
            this.password = password;
        }

        public void encodePassword(String encodePassword) {
            this.password = encodePassword;
        }

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .activated(true)
                    .build();
        }
    }



    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {

        private int code;
        private String message;
        private String username;

        @Builder
        public Response(int code, String message, String username) {
            this.code = code;
            this.message = message;
            this.username = username;
        }

        public static Response of(User user) {
            RegisterEnum registerEnum = user.getId() > 0 ? RegisterEnum.SUCCESS : RegisterEnum.FAIL;
            return Response.builder()
                    .code(registerEnum.getCode())
                    .message(registerEnum.getMessage())
                    .username(user.getUsername())
                    .build();
        }
    }
}