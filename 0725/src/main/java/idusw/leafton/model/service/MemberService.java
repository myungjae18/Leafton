package idusw.leafton.model.service;

import idusw.leafton.model.DTO.MemberDTO;

public interface MemberService {
    //로그인 성공 여부를 반환하는 메서드
    public MemberDTO loginCheck(MemberDTO memberDTO);

    //email 중복 여부를 확인하는 메서드
    public MemberDTO emailCheck(String email);

    //Member 객체를 받아 회원가입 처리를 지시하는 메서드
    public MemberDTO register(MemberDTO memberDTO);
}
