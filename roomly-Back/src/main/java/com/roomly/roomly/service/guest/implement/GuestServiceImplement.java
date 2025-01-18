package com.roomly.roomly.service.guest.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roomly.roomly.dto.request.guest.PatchGuestAuthRequestDto;
import com.roomly.roomly.dto.request.guest.PatchGuestPwRequestDto;
import com.roomly.roomly.dto.request.guest.GuestInformationRequestDto;
import com.roomly.roomly.dto.response.ResponseDto;
import com.roomly.roomly.dto.response.guest.GetGuestMyPageResponseDto;
import com.roomly.roomly.dto.response.guest.GetGuestSignInResponseDto;
import com.roomly.roomly.entity.GuestEntity;
import com.roomly.roomly.provider.SmsProvider;
import com.roomly.roomly.repository.GuestRepository;
import com.roomly.roomly.repository.TelAuthNumberRepository;
import com.roomly.roomly.service.guest.GuestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestServiceImplement implements GuestService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SmsProvider smsProvider;
    private final GuestRepository guestRepository;
    private final TelAuthNumberRepository telAuthNumberRepository;

    // 게스트Id에 관한 MyPageList 메서드
    @Override
    public ResponseEntity<? super GetGuestMyPageResponseDto> getGuestMyPage(String guestId, GuestInformationRequestDto dto) {

        GuestEntity guestEntity = null;
        String guestPw = dto.getGuestPw();

        try {

            guestEntity = guestRepository.findByGuestId(guestId);
            if (guestEntity == null) return ResponseDto.noExistUserId();
            
            String basicPw = guestEntity.getGuestPw();
            
            boolean isMatched = passwordEncoder.matches(guestPw,basicPw);
            if(!isMatched) return ResponseDto.noPermission();
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetGuestMyPageResponseDto.success(guestEntity);
    }

    // 비밀번호 수정 메서드(로그인)
    @Override
    public ResponseEntity<ResponseDto> patchGuestPw(
            PatchGuestPwRequestDto dto, String guestId) {

        String currentGuestPw = dto.getCurrentGuestPw();
        String changeGuestPw = dto.getChangeGuestPw();

        try {

            GuestEntity guestEntity = guestRepository.findByGuestId(guestId);
            if (guestEntity == null)
                return ResponseDto.noExistUserId();
    
            String basicPw = guestEntity.getGuestPw();
            boolean isMatched = passwordEncoder.matches(currentGuestPw, basicPw);
            if (!isMatched) return ResponseDto.notMatchValue();

            String encodedPassword = passwordEncoder.encode(changeGuestPw);
            dto.setChangeGuestPw(encodedPassword);
            guestEntity.patchPw(dto);
            guestRepository.save(guestEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 전화번호 수정 및 기존번호 삭제 메서드
    @Override
    @Transactional
    public ResponseEntity<ResponseDto> patchGuestAuth(
            PatchGuestAuthRequestDto dto, String guestId) {

        String telNumber = dto.getGuestTelNumber();
        String authNumber = dto.getGuestAuthNumber();

        try {

            GuestEntity guestEntity = guestRepository.findByGuestId(guestId);
            if (guestEntity == null) return ResponseDto.noExistUserId();
            
                boolean isMatchedAuth = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatchedAuth) return ResponseDto.telAuthFail();

            String oldTelNumber = guestEntity.getGuestTelNumber();

            guestEntity.patchTelNumber(dto);
            guestRepository.save(guestEntity);

            telAuthNumberRepository.deleteByTelNumber(oldTelNumber);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    // 게스트 정보 불러오기
    public ResponseEntity<? super GetGuestSignInResponseDto> getGuestSignIn(String userId) {
        
        GuestEntity guestEntity = null;

        try {
            
            guestEntity = guestRepository.findByGuestId(userId);
            if(guestEntity == null) return ResponseDto.noExistUserId();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetGuestSignInResponseDto.success(guestEntity);
    }

}