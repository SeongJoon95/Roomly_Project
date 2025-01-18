package com.roomly.roomly.service.auth;

import org.springframework.http.ResponseEntity;

import com.roomly.roomly.dto.request.auth.common.PwFindRequestDto;
import com.roomly.roomly.dto.request.auth.common.IdFindAuthNumberRequestDto;
import com.roomly.roomly.dto.request.auth.common.IdFindRequestDto;
import com.roomly.roomly.dto.request.auth.common.TelAuthCheckRequestDto;
import com.roomly.roomly.dto.request.auth.common.TelNumberRequestDto;
import com.roomly.roomly.dto.request.auth.guestauth.GuestIdCheckRequestDto;
import com.roomly.roomly.dto.request.auth.guestauth.GuestSignInRequestDto;
import com.roomly.roomly.dto.request.auth.guestauth.GuestSignUpRequestDto;
import com.roomly.roomly.dto.request.auth.hostauth.HostBusinessImageRequestDto;
import com.roomly.roomly.dto.request.auth.hostauth.HostBusinessNumberRequestDto;
import com.roomly.roomly.dto.request.auth.hostauth.HostIdCheckRequestDto;
import com.roomly.roomly.dto.request.auth.hostauth.HostSignInRequestDto;
import com.roomly.roomly.dto.request.auth.hostauth.HostSignUpRequestDto;
import com.roomly.roomly.dto.response.ResponseDto;
import com.roomly.roomly.dto.response.guestauth.GuestSignInResponseDto;
import com.roomly.roomly.dto.response.hostauth.HostSignInResponseDto;

public interface AuthService {

    //! -------- 공용 --------
    
    // 전화번호 중복확인 및 인증번호 전송
    ResponseEntity<ResponseDto> telNumber(TelNumberRequestDto dto);
    // 전화번호 인증번호 확인
    ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);
    // Id 찾기에 관한 인증번호 전송
    ResponseEntity<ResponseDto> idFindAuthNumber(IdFindAuthNumberRequestDto dto);
    // Id값 반환
    ResponseEntity<ResponseDto> idFind(IdFindRequestDto dto);
    // 비밀번호 변경
    ResponseEntity<ResponseDto> pwFind(PwFindRequestDto dto);
    

    //! -------- 호스트 --------

    // 호스트 아이디 중복확인
    ResponseEntity<ResponseDto> hostIdCheck(HostIdCheckRequestDto dto);
    // 사업자 번호 중복확인
    ResponseEntity<ResponseDto> hostBusinessNumber(HostBusinessNumberRequestDto dto);
    // 사업자 이미지 전송
    ResponseEntity<ResponseDto> hostBusinessImage(HostBusinessImageRequestDto dto);
    // 호스트 회원가입
    ResponseEntity<ResponseDto> hostSignUp(HostSignUpRequestDto dto);
    // 호스트 로그인
    ResponseEntity<? super HostSignInResponseDto> hostSignIn(HostSignInRequestDto dto);

    //! -------- 게스트 --------

    // 게스트 아이디 중복확인
    ResponseEntity<ResponseDto> guestIdCheck(GuestIdCheckRequestDto dto);
    // 게스트 회원가입
    ResponseEntity<ResponseDto> guestSignUp(GuestSignUpRequestDto dto);
    // 게스트 로그인
    ResponseEntity<? super GuestSignInResponseDto> guestSignIn(GuestSignInRequestDto dto);
}
