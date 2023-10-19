package com.routemind.orderkit.controller;

import com.routemind.orderkit.exception.UserNotFoundException;
import com.routemind.orderkit.model.entity.User;
import com.routemind.orderkit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User service request controller", description = "User service API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = User.class)))})
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        log.info("Get user by id: {}", id);
        User user = userService.findUserById(id);
        if (user == null) {
            log.debug("User by id {} not found", id);
            throw new UserNotFoundException(String.format("User not found by id: %d", id));
        }
        log.info("Result user: {}", user);
        return user;
    }

    @Operation(summary = "Get user by email address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = User.class)))})
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable @Email String email) throws UserNotFoundException {
        log.info("Get user by email: {}", email);
        User user = userService.findUserByEmail(email);
        if (user == null) {
            log.info("User by email {} not found", email);
            throw new UserNotFoundException(String.format("User not found by email: %s", email));
        }
        log.info("Result user: {}", user);
        return user;
    }

    @Operation(summary = "Create user by userDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = User.class)))})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        log.info("Create user from request body: {}", user);
        return userService.createUser(user);
    }

    @Operation(summary = "Update user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = User.class)))})
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable @Positive long id, @RequestBody User user) throws UserNotFoundException {
        log.info("Update user fields: {}", user);
        return userService.updateUser(id, user);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = User.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @Positive long id) throws UserNotFoundException {
        log.info("Delete user by id: {}", id);
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}