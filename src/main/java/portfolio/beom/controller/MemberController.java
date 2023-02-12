package portfolio.beom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import portfolio.beom.domain.member.SessionConst;
import portfolio.beom.argumentresolver.Login;
import portfolio.beom.domain.member.Member;
import portfolio.beom.domain.member.request.LoginMemberRequest;
import portfolio.beom.domain.member.request.SaveMemberRequest;
import portfolio.beom.domain.member.response.LoginMemberResponse;
import portfolio.beom.domain.member.response.SaveMemberResponse;
import portfolio.beom.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members/save")
    public ResponseEntity<SaveMemberResponse> save(@RequestBody SaveMemberRequest request) {
        SaveMemberResponse saveMember = memberService.save(request);
        return new ResponseEntity<>(saveMember, HttpStatus.CREATED);
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody LoginMemberRequest loginMemberRequest,
                                                     HttpServletRequest request) {
        LoginMemberResponse memberResponse = memberService.login(loginMemberRequest);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberResponse);

        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @GetMapping
    public String loginArgumentResolver(@Login Member loginMember) {
        if (loginMember == null) {
            return "없음";
        }
        return "로그인";
    }
}
