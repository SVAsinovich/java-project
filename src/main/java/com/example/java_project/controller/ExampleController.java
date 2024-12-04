package com.example.java_project.controller;

import com.example.java_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Example", description = "Example controller with different permission")
public class ExampleController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "Endpoint for authorization users")
    public String example() {
        return "Hello, world!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Endpoint for authorization users with ADMIN role")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Add ADMIN role to user")
    public void getAdmin() {
        service.getAdmin();
    }
}
