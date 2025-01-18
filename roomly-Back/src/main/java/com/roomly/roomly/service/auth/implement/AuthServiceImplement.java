package com.roomly.roomly.service.auth.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.roomly.roomly.common.util.AuthNumberCreater;
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
import com.roomly.roomly.dto.response.guest.GuestIdFindSuccessResponseDto;
import com.roomly.roomly.dto.response.guestauth.GuestSignInResponseDto;
import com.roomly.roomly.dto.response.host.HostIdFindSuccessResponseDto;
import com.roomly.roomly.dto.response.hostauth.HostSignInResponseDto;
import com.roomly.roomly.entity.GuestEntity;
import com.roomly.roomly.entity.HostEntity;
import com.roomly.roomly.entity.TelAuthNumberEntity;
import com.roomly.roomly.provider.JwtProvider;
import com.roomly.roomly.provider.SmsProvider;
import com.roomly.roomly.repository.GuestRepository;
import com.roomly.roomly.repository.HostRepository;
import com.roomly.roomly.repository.TelAuthNumberRepository;
import com.roomly.roomly.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final HostRepository hostRepository;
    private final SmsProvider smsProvider;
    private final TelAuthNumberRepository telAuthNumberRepository;
    private final GuestRepository guestRepository;

    private  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;

//! ------------------------------------------ 공용 ------------------------------------------ 
    
    @Override
    // 전화번호 중복확인 및 인증번호 발송
    public ResponseEntity<ResponseDto> telNumber(TelNumberRequestDto dto) {
        
        String telNumber = dto.getTelNumber();
        
        try {
            
            boolean isMatchedTelNumber = telAuthNumberRepository.existsByTelNumber(telNumber);
            if(isMatchedTelNumber) return ResponseDto.duplicatedTelNumber();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = AuthNumberCreater.number4();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if(!isSendSuccess) return ResponseDto.messageSendFail();

        try {
            TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telNumber, authNumber);
            telAuthNumberRepository.save(telAuthNumberEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    // 인증번호 확인 메서드 (회원가입 / 아이디 찾기)
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
        
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {
            
            boolean isMatchedTelAuth = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatchedTelAuth) return ResponseDto.telAuthFail();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

@Override
    // id 찾기에 관한 인증번호 전송
    public ResponseEntity<ResponseDto> idFindAuthNumber(IdFindAuthNumberRequestDto dto) {
        
        String name = dto.getName();
        String telNumber = dto.getTelNumber();

        try {
            
            boolean isMatched =  
            guestRepository.existsByGuestNameAndGuestTelNumber(name, telNumber) || 
            hostRepository.existsByHostNameAndHostTelNumber(name, telNumber);

            if (!isMatched) {
                return ResponseDto.notMatchValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = AuthNumberCreater.number4();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if (!isSendSuccess)
            return ResponseDto.messageSendFail();

        try {
            TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telNumber, authNumber);
            telAuthNumberRepository.save(telAuthNumberEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    // Id값 반환
    public ResponseEntity<ResponseDto> idFind(IdFindRequestDto dto) {
        
        String name = dto.getName();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {
            
            //게스트
            GuestEntity guestEntity = guestRepository.findByGuestNameAndGuestTelNumber(name, telNumber);

            if (guestEntity != null) {

                boolean isMatchedTelAuth = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);

                if (isMatchedTelAuth) {

                    GuestIdFindSuccessResponseDto guestResponse = new GuestIdFindSuccessResponseDto(guestEntity);
                    return ResponseEntity.ok((ResponseDto) guestResponse);

                } else return ResponseDto.telAuthFail();   

            } 

            // 호스트
            HostEntity hostEntity = hostRepository.findByHostNameAndHostTelNumber(name, telNumber);
            
            if (hostEntity != null) {

                boolean isMatchedTelAuth = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);

                if(isMatchedTelAuth) {
                    HostIdFindSuccessResponseDto hostResponse = new HostIdFindSuccessResponseDto(hostEntity);
                    return ResponseEntity.ok((ResponseDto) hostResponse);
                } else return ResponseDto.telAuthFail();   
            } else return ResponseDto.notMatchValue();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        // return ResponseDto.success();
    }

    @Override
    // 비밀변호 변경(로그아웃)
    public ResponseEntity<ResponseDto> pwFind(PwFindRequestDto dto) {
        
        String id = dto.getId();
        String telNumber = dto.getTelNumber();
        String pw = dto.getPw();
        String userType = dto.getUserType();

        try {

            if ("guest".equalsIgnoreCase(userType)) {
                GuestEntity guestEntity = guestRepository.findByGuestIdAndGuestTelNumber(id,telNumber);
                if(guestEntity == null) return ResponseDto.noExistGuest();
                
                String encodedPassword = passwordEncoder.encode(pw);
                guestEntity.setGuestPw(encodedPassword);
                guestRepository.save(guestEntity);
                
            } else if("host".equalsIgnoreCase(userType)) {
                HostEntity hostEntity = hostRepository.findByHostId(id);
                if(hostEntity == null) return ResponseDto.noExistHost();
                
                String encodedPassword1 = passwordEncoder.encode(pw);
                hostEntity.setHostPw(encodedPassword1);
                hostRepository.save(hostEntity);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();   
        }

        return ResponseDto.success();

    }

//!  ------------------------------------------ 호스트 ------------------------------------------ 
    // 호스트 아이디 중복확인 메서드
    @Override
    public ResponseEntity<ResponseDto> hostIdCheck(HostIdCheckRequestDto dto) {
        String hostId = dto.getHostId();
        try {
            
            boolean isMatched = hostRepository.existsByHostId(hostId);
            if (isMatched) return ResponseDto.duplicatedId();

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    // 호스트 사업자 번호 중복확인 메서드
    @Override
    public ResponseEntity<ResponseDto> hostBusinessNumber(HostBusinessNumberRequestDto dto) {
        String hostBusinessNumber = dto.getHostBusinessNumber();
        try {
            
            boolean isMatchedHostBusinessNumber = hostRepository.existsByHostBusinessNumber(hostBusinessNumber);
            if(isMatchedHostBusinessNumber) return ResponseDto.duplicatedBusinessNumber();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    // 사업자 파일 중복확인 메서드
    @Override
    public ResponseEntity<ResponseDto> hostBusinessImage(HostBusinessImageRequestDto dto) {
        try {
            String businessImage = dto.getBusinessImage();
            boolean isExist = hostRepository.existsByBusinessImage(businessImage);
            if (isExist) return ResponseDto.duplicatedImage(); 

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    // 호스트 회원가입 메서드
    @Override
    public ResponseEntity<ResponseDto> hostSignUp(HostSignUpRequestDto dto) {
        
        String hostId = dto.getHostId();
        String hostPw = dto.getHostPw();
        String hostTelNumber = dto.getHostTelNumber();
        String hostAuthNumber = dto.getHostAuthNumber();
        String hostBusinessNumber = dto.getHostBusinessNumber();
        String businessImage = dto.getBusinessImage();

        try {

            boolean isMatchedHostId = hostRepository.existsByHostId(hostId);
            if(isMatchedHostId) return ResponseDto.duplicatedId();

            boolean isMatchedTelNumber = hostRepository.existsByHostTelNumber(hostTelNumber);
            if(isMatchedTelNumber) return ResponseDto.duplicatedTelNumber();
            
            boolean isMatchedTelAuth = telAuthNumberRepository.existsByTelNumberAndAuthNumber(hostTelNumber, hostAuthNumber);
            if(!isMatchedTelAuth) return ResponseDto.telAuthFail();

            boolean isMatchedHostBusinessNumber = hostRepository.existsByHostBusinessNumber(hostBusinessNumber);
            if (isMatchedHostBusinessNumber) return ResponseDto.duplicatedBusinessNumber();

            boolean isExist = hostRepository.existsByBusinessImage(businessImage);
            if (isExist) return ResponseDto.duplicatedImage();

            String encodedPassword = passwordEncoder.encode(hostPw);
            dto.setHostPw(encodedPassword);

            HostEntity hostEntity = new HostEntity(dto);
            hostRepository.save(hostEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    // 호스트 로그인 메서드
    @Override
    public ResponseEntity<? super HostSignInResponseDto> hostSignIn(HostSignInRequestDto dto) {

        String accessToken = null;
        String hostId = dto.getHostId();
        String hostPw = dto.getHostPw();
        
        try {
            
            HostEntity hostEntity = hostRepository.findByHostId(hostId);
            if(hostEntity == null) return ResponseDto.noExistUserId();

            String encodedPassword = hostEntity.getHostPw();
            boolean isMatchedHostPassword = passwordEncoder.matches(hostPw,encodedPassword);
            if(!isMatchedHostPassword) return ResponseDto.signInFail();

            accessToken = jwtProvider.createHostToken(hostId);
            if(accessToken == null) return ResponseDto.tokenCreateFail();
            

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return HostSignInResponseDto.success(accessToken);
    }


//! ------------------------------------------ 게스트 ------------------------------------------ 

    @Override
    // 아이디 중복확인 메서드
    public ResponseEntity<ResponseDto> guestIdCheck(GuestIdCheckRequestDto dto) {

        String guestId = dto.getGuestId();

        try {
            boolean isExistedId = guestRepository.existsByGuestId(guestId);
            if (isExistedId) return ResponseDto.duplicatedId();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }


    @Override
    // 게스트회원가입 메서드
    public ResponseEntity<ResponseDto> guestSignUp(GuestSignUpRequestDto dto) {
        
        String guestId = dto.getGuestId();
        String guestTelNumber = dto.getGuestTelNumber();
        String guestAuthNumber = dto.getGuestAuthNumber();
        String guestPassword = dto.getGuestPw();
        
        try {
            
            boolean isExistedId = guestRepository.existsByGuestId(guestId);
            if(isExistedId) return ResponseDto.duplicatedId();

            boolean isExistedTelNumber = guestRepository.existsByGuestTelNumber(guestTelNumber);
            if(isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(guestTelNumber, guestAuthNumber);
            if(!isMatched) return ResponseDto.telAuthFail();

            String encodedPassword = passwordEncoder.encode(guestPassword);
            dto.setGuestPw(encodedPassword);

            GuestEntity guestEntity = new GuestEntity(dto);
            guestRepository.save(guestEntity);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    // 로그인 메서드
    public ResponseEntity<? super GuestSignInResponseDto> guestSignIn(GuestSignInRequestDto dto) {
        
        String guestId = dto.getGuestId();
        String password = dto.getGuestPw();
        String accessToken = null;

        try {
            
            GuestEntity guestEntity = guestRepository.findByGuestId(guestId);
            if(guestEntity == null) return ResponseDto.signInFail();

            String encodedPassword = guestEntity.getGuestPw();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return ResponseDto.signInFail();

            accessToken = jwtProvider.createGuestToken(guestId);
            if(accessToken == null) return ResponseDto.tokenCreateFail();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GuestSignInResponseDto.success(accessToken);
    }
    
}
