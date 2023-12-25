package com.bet.sportoto.account.controller;

import com.bet.sportoto.exception.ErrorResponse;
import com.bet.sportoto.account.controller.request.LoginRequest;
import com.bet.sportoto.account.controller.request.RegisterRequest;
import com.bet.sportoto.account.service.AccountService;
import com.bet.sportoto.security.annotations.IsAuthentificated;
import com.bet.sportoto.user.entity.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @Operation(
            description = "creates a user entity",
            summary = "CREATE an user entity.",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest request) {
        return accountService.register(request);
    }

    @PostMapping("/login")
    @Operation(
            description = "login to system with mail and password.",
            summary = "Account Login",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "UnAuthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return accountService.login(request);
    }

    @IsAuthentificated
    @Operation(
            description = "returns the current logged in user",
            summary = "Who I Am ?",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "UnAuthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getMe() {
        return accountService.getMe();
    }

    @Operation(
            description = "Updates the password of the user whose mail address is specified and sends the new password to the mail address",
            summary = "Forgot your password?",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(description = "UnAuthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            })
    @PutMapping("/forgotpassword")
    @ResponseStatus(HttpStatus.OK)
    public String putPassword(@RequestParam String mail) {
        return accountService.putPassword(mail);
    }

}
