package portfolio.beom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.beom.argumentresolver.LoginUser;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.argumentresolver.SessionConst;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.request.UpdateMemberRequest;
import portfolio.beom.dto.response.SaveMemberResponse;
import portfolio.beom.dto.response.UpdateMemberResponse;
import portfolio.beom.exception.FailedUpdateMemberException;
import portfolio.beom.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SaveMemberResponse> memberSave(@RequestBody @Valid SaveMemberRequest request) {
        SaveMemberResponse saveMember = memberService.save(request);
        return new ResponseEntity<>(saveMember, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<UpdateMemberResponse> memberUpdate(@LoginUser MemberSession memberSession,
                                                             @PathVariable Long memberId,
                                                             @RequestBody UpdateMemberRequest updateMemberRequest){

        if (Objects.equals(memberId, memberSession.getMemberId())) {
            UpdateMemberResponse updateMember = memberService.update(memberId, updateMemberRequest);
            return new ResponseEntity<>(updateMember, HttpStatus.OK);
        }
        throw new FailedUpdateMemberException();
    }

    @DeleteMapping("/{memberId}")
    public String memberDelete(@PathVariable Long memberId, HttpServletRequest request) {

        HttpSession session = request.getSession();
        MemberSession attribute = (MemberSession) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (attribute != null) {
            memberService.delete(memberId);
            return "회원삭제 완료되었습니다.";
        }
        return "본인 외에는 삭제할 수 없습니다.";
    }
}
