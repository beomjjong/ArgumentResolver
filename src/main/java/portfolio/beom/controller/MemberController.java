package portfolio.beom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.argumentresolver.SessionConst;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.request.UpdateMemberRequest;
import portfolio.beom.dto.response.SaveMemberResponse;
import portfolio.beom.dto.response.UpdateMemberResponse;
import portfolio.beom.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<SaveMemberResponse> memberSave(@RequestBody SaveMemberRequest request) {
        SaveMemberResponse saveMember = memberService.save(request);
        return new ResponseEntity<>(saveMember, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<UpdateMemberResponse> memberUpdate(@PathVariable Long memberId,
                                                             @RequestBody UpdateMemberRequest updateMemberRequest,
                                                             HttpServletRequest request){

        HttpSession session = request.getSession(false);

        MemberSession attribute = (MemberSession) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (attribute != null){
            UpdateMemberResponse updateMember = memberService.update(memberId, updateMemberRequest);
            return new ResponseEntity<>(updateMember, HttpStatus.OK);
        }
        throw new IllegalArgumentException("업데이트 실패");
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
    // 게시글 작성. 본인이 로그인을 했으니까 게시글 작성은 제목 내용만 적으면 작성이 되고, 게시글 조회를 했을 떄 제목 내용 작성자 가 표시 되어야 한다.


}
