package com.roomly.roomly.dto.request.auth.common;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class IdFindRequestDto {
    
    private String name;
    private String telNumber;
    private String authNumber;
}
