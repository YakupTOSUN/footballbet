package com.bet.sportoto.user.controller;

import com.bet.sportoto.exception.ErrorResponse;
import com.bet.sportoto.security.annotations.IsAuthentificated;
import com.bet.sportoto.security.annotations.OnlyAdmin;
import com.bet.sportoto.user.entity.UserDto;
import com.bet.sportoto.user.entity.UserRole;
import com.bet.sportoto.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @IsAuthentificated
    @Operation(
            summary = "Updates the password of an existing user entity by ID",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PatchMapping("/changedpassword")
    @ResponseStatus(HttpStatus.CREATED)
    public void changedPassword(@RequestParam String userId, @RequestParam String newPassword) {
        userService.changedPassword(userId, newPassword);
    }

    @OnlyAdmin
    @Operation(
            summary = "Updates the role of an existing user entity by ID",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PatchMapping("/changedrole")
    @ResponseStatus(HttpStatus.CREATED)
    public void changedRole(@RequestParam String userId, @RequestParam UserRole userRole) {
        userService.changedRole(userId, userRole);
    }

    @OnlyAdmin
    @Operation(
            summary = "Returns all existing users",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

}
