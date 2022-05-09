package com.ljm.spring_boot.jwt.controller;

import com.ljm.spring_boot.jwt.domain.Users;
import com.ljm.spring_boot.jwt.dto.UsersDTO;
import com.ljm.spring_boot.jwt.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signup(@Valid @RequestBody UsersDTO usersDTO) {
        return ResponseEntity.ok(usersService.signup(usersDTO));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Users> getMyUserInfo() {
        return ResponseEntity.ok(usersService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Users> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(usersService.getUserWithAuthorities(username).get());
    }
}
