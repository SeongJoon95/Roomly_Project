// interface: 호스트 전화번호 변경 및 삭제 dto //

export default interface PatchHostTelNumberRequestDto {
    telNumber: string;
    authNumber: string;
}
