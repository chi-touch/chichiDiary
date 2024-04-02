package africa.semicolon.chichidiary.controller;

import africa.semicolon.chichidiary.dto.request.LoginRequest;
import africa.semicolon.chichidiary.dto.request.RegisterRequest;
import africa.semicolon.chichidiary.dto.response.LoginResponse;
import africa.semicolon.chichidiary.dto.response.RegisterResponse;
import africa.semicolon.chichidiary.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(RegisterRequest request){
        RegisterResponse response = diaryService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("login")
    public ResponseEntity<LoginResponse>login(LoginRequest loginRequest){
        var response = diaryService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
