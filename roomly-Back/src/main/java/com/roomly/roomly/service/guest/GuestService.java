package com.roomly.roomly.service.guest;

import org.springframework.http.ResponseEntity;

import com.roomly.roomly.dto.request.guest.PatchGuestAuthRequestDto;
import com.roomly.roomly.dto.request.guest.PatchGuestPwRequestDto;
import com.roomly.roomly.dto.request.guest.GuestInformationRequestDto;
import com.roomly.roomly.dto.response.ResponseDto;
import com.roomly.roomly.dto.response.guest.GetGuestMyPageResponseDto;
import com.roomly.roomly.dto.response.guest.GetGuestSignInResponseDto;

public interface GuestService {
    
    // Guest 정보 보기
    ResponseEntity <? super GetGuestMyPageResponseDto> getGuestMyPage(String guestId, GuestInformationRequestDto dto);
    // Guest 비밀번호 수정(로그인)
    ResponseEntity<ResponseDto> patchGuestPw(PatchGuestPwRequestDto dto, String guestId);
    // Guest 인증및 이전번호 삭제
    ResponseEntity<ResponseDto> patchGuestAuth(PatchGuestAuthRequestDto dto, String guestId);
    // 게스트 정보 불러오기
    ResponseEntity<? super GetGuestSignInResponseDto> getGuestSignIn(String userId);
}
