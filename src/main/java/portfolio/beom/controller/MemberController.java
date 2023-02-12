package portfolio.beom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.argumentresolver.SessionConst;
import portfolio.beom.argumentresolver.Login;
import portfolio.beom.dto.request.LoginMemberRequest;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.response.LoginMemberResponse;
import portfolio.beom.dto.response.SaveMemberResponse;
import portfolio.beom.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    //todo restful api level 공부 -> ex level 0 ... 1 리소스 자원 절약 관련 키워드.
    @PostMapping("/api/members") //todo get -> post
    public ResponseEntity<SaveMemberResponse> save(@RequestBody @Valid SaveMemberRequest request) {
        SaveMemberResponse saveMember = memberService.save(request);
        return new ResponseEntity<>(saveMember, HttpStatus.CREATED);
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody LoginMemberRequest loginMemberRequest,
                                                     HttpServletRequest request) {
        LoginMemberResponse memberResponse = memberService.login(loginMemberRequest);


        //우리가 프로젝트에서 세션으로 사용할 객체.
        MemberSession memberSession = MemberSession.builder()
                .memberId(memberResponse.getId())
                .email(memberResponse.getEmail())
                .build();

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberSession);

        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    @GetMapping("/")
    public String loginArgumentResolver(@Login MemberSession session) {
        if (session == null) {
            return "없음";
        }
        return "로그인";
    }

    @DeleteMapping("/api/members/{memberId}")
    public String deleteMember(@Login MemberSession session, @PathVariable Long memberId) {

        //todo aop
    }


    // todo Session 객체로 사용한 객체 새로 만들었고, 예외처리 공부하고, 본인 글은 본인만 수정 삭제 가능. 다른 회원이 불가능.
}
