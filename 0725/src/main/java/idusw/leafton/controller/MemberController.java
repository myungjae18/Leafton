package idusw.leafton.controller;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.DTO.StyleDTO;
import idusw.leafton.model.service.MemberService;
import idusw.leafton.model.service.StyleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //마이 페이지로 이동
    @GetMapping(value="/myPage")
    private String goMyPage(HttpServletRequest request) {
        return "/member/info";
    }

    //로그아웃 요청을 처리하는 메서드
    @GetMapping(value="/logout")
    private String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();//세션 회수

        return "/main/index";
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

    //회원가입을 처리하는 메서드
    @PostMapping(value="/register/{styleId}")
    private String register(@PathVariable("styleId") Long styleId, @ModelAttribute MemberDTO memberDTO, HttpServletRequest request){
        //이메일 중복체크 후 중복이 아닐 경우 가입 로직으로 진행
        if (memberService.emailCheck(memberDTO.getEmail()) != null) {
            request.setAttribute("message", "이미 사용 중인 이메일입니다");
            return "/member/login";
        } else {
            //memberDTO에 넣을 styleDTO 생성하여 pathVariable로 받은 styleId 주입
            StyleDTO styleDTO = new StyleDTO();
            styleDTO.setStyleId(styleId);
            memberDTO.setStyleDTO(styleDTO);//memberDTO에 styleDTO 주입

            //entity에 insert
            memberService.register(memberDTO);
        }

        return "redirect:/main/index";
    }

    //회원 정보 수정 요청을 처리하는 메서드
    @PostMapping(value="/edit")
    private String edit(HttpServletRequest request, @RequestParam String type, @ModelAttribute MemberDTO memberDTO){
        String url = null;
        HttpSession session = request.getSession();

        if(type.equals("edit")) {
            System.out.println(request.getParameter("zipcode"));

            StyleDTO styleDTO = new StyleDTO();
            styleDTO.setStyleId(Long.valueOf(request.getParameter("styleId")));
            memberDTO.setStyleDTO(styleDTO);
            MemberDTO result = memberService.register(memberDTO);

            session.removeAttribute("memberDTO");
            session.setAttribute("memberDTO", result);

            url = "redirect:/member/myPage";
        }else if(type.equals("delete")) {
            memberService.withdraw(Long.valueOf(request.getParameter("memberId")));

            session.removeAttribute("memberDTO");
            url = "redirect:/main/index";
        }

        return url;
    }
}
