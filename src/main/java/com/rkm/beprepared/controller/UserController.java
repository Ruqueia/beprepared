package com.rkm.beprepared.controller;

import com.rkm.beprepared.dto.request.UserRequestDto;
import com.rkm.beprepared.dto.response.StatsResponse;
import com.rkm.beprepared.dto.response.UserResponseDto;
import com.rkm.beprepared.mapper.Mapper;
import com.rkm.beprepared.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "5. User controller")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(
                mapper.mapUserRequestToModel(userRequestDto)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto>getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.mapUserToResponseDto(
                userService.getUserById(id)));
    }

    @GetMapping("/matrics")
    public ResponseEntity<StatsResponse> getMatrics(){
        return ResponseEntity.ok(userService.getAllStats());
    }
}
