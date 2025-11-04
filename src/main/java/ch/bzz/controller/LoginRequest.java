package ch.bzz.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String projectName;
    private String password;
}
