package idusw.leafton.controller;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.StyleDTO;
import idusw.leafton.model.service.MemberService;
import idusw.leafton.model.service.StyleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping(value = "/member")
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    StyleService styleService;

    //로그인 페이지로 이동
    @GetMapping(value = "/login")
    private String goLogin(HttpServletRequest request) {
        request.setAttribute("type", request.getParameter("type"));
        return "/member/login";
    }

    //로그인 요청을 처리하는 메서드
    @PostMapping(value="/login")
    private String login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request){
        //view에서 넘어온 email과 password를 이용하여 select
        MemberDTO memberResult = memberService.loginCheck(memberDTO);

        if(memberResult != null) //로그인 성공 시
        {
            HttpSession session = request.getSession(); //session 객체 생성
            session.setAttribute("memberDTO", memberResult); //session에 DTO 주입
            return "/main/index";
        } else { //로그인 실패 시
            //request에 message 주입 후 다시 로그인 페이지로 이동
            request.setAttribute("message", "아이디나 비밀번호가 일치하지 않습니다");
            return "/member/login";
        }
    }

    //로그아웃 요청을 처리하는 메서드
    @GetMapping(value="/logout")
    private String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();//세션 회수

        return "/main/index";
    }

    //회원가입 요청을 처리하는 메서드
    @PostMapping(value="/register")
    private String register(HttpServletRequest request){
        //이메일 중복체크 후 중복이 아닐 경우 가입 로직으로 진행
        MemberDTO memberDTO = memberService.emailCheck(request.getParameter("email"));
        if (memberDTO != null) {
            request.setAttribute("message", "이미 사용 중인 이메일입니다");
            return "/member/login";
        } else {
            //request를 받아 DTO 객체를 이용하여 style과 member relation에 insert
            memberDTO = new MemberDTO();
            //style의 pk를 이용하여 select 수행
            StyleDTO styleDTO = styleService.findById(Long.valueOf(request.getParameter("styleId")));

            //memberDTO에 request로 받아온 정보 주입
            memberDTO.setEmail(request.getParameter("email"));
            memberDTO.setPassword(request.getParameter("password"));
            memberDTO.setName(request.getParameter("name"));
            memberDTO.setAge(Integer.valueOf(request.getParameter("age")));
            memberDTO.setPhone(request.getParameter("phone"));
            memberDTO.setAddress(request.getParameter("address"));
            memberDTO.setZipcode(Integer.valueOf(request.getParameter("zipcode")));
            memberDTO.setGender(Integer.valueOf(request.getParameter("gender")));
            memberDTO.setStyleDTO(styleDTO);

            //entity에 insert
            memberService.register(memberDTO);
        }
        return "/main/index";
    }
}
