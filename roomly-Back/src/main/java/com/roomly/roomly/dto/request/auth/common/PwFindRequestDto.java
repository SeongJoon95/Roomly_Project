package com.roomly.roomly.dto.request.auth.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PwFindRequestDto {
    
    @NotBlank
    @Pattern(regexp="^(guest|host)$")
    private String userType;
    @NotBlank
    private String id;
    @NotBlank
    private String telNumber;
    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String pw;
}
