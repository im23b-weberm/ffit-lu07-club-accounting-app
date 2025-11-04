package ch.bzz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ch.bzz.controller.LoginRequest;
import ch.bzz.controller.LoginProject200Response;

@RequestMapping("/api/project")
public interface ProjectApi {

    @PostMapping("/register")
    ResponseEntity<Void> createProject(@RequestBody LoginRequest loginRequest);

    @PostMapping("/login")
    ResponseEntity<LoginProject200Response> loginProject(@RequestBody LoginRequest loginRequest);
}
