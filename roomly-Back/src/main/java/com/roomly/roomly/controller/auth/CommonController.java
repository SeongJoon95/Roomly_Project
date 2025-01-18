package com.roomly.roomly.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roomly.roomly.dto.request.auth.common.PwFindRequestDto;
import com.roomly.roomly.dto.request.auth.common.IdFindAuthNumberRequestDto;
import com.roomly.roomly.dto.request.auth.common.IdFindRequestDto;
import com.roomly.roomly.dto.request.auth.common.TelAuthCheckRequestDto;
import com.roomly.roomly.dto.request.auth.common.TelNumberRequestDto;
import com.roomly.roomly.dto.response.ResponseDto;
import com.roomly.roomly.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roomly/auth/common")
@RequiredArgsConstructor
public class CommonController {
    
    private final AuthService authService;


    // 전화번호 중복확인 및 인증번호 발송 api
    @PostMapping("/tel-auth")
    public ResponseEntity<ResponseDto> telAuth(
        @RequestBody @Valid TelNumberRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.telNumber(requestBody);
        return response;
    }
    
    // 해당 전화번호에 대한 인증번호 확인 api
    @PostMapping("/tel-auth-check")
    public ResponseEntity<ResponseDto> telAuthCheck(
        @RequestBody @Valid TelAuthCheckRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.telAuthCheck(requestBody);
        return response;
    }

    // Id 찾기에 관한 인증번호 발송 api
    @PatchMapping("id-find-auth-number")
    public ResponseEntity<ResponseDto> idFindAuthNumber(
        @RequestBody @Valid IdFindAuthNumberRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> responseBody = authService.idFindAuthNumber(requestBody);
        return responseBody;
    }

    // Id 찾기
    @PostMapping("id-find")
    public ResponseEntity<ResponseDto> idFind(
        @RequestBody @Valid IdFindRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> responseBody = authService.idFind(requestBody);
        return responseBody;
    }
    
    // 비밀번호 변경(로그아웃상태)
    @PatchMapping("/pw-find")
    public ResponseEntity<ResponseDto> pwFind(
        @RequestBody @Valid PwFindRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> responseBody = authService.pwFind(requestBody);
        return responseBody;
    }

}
