// interface: 게스트 전화번호 수정및 기존번호 삭제

export default interface PatchGuestAuthRequestDto {

    guestTelNumber: string;
    guestAuthNumber: string;
}