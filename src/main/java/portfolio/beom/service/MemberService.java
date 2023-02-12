package portfolio.beom.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.beom.domain.member.Member;
import portfolio.beom.dto.request.LoginMemberRequest;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.response.LoginMemberResponse;
import portfolio.beom.dto.response.SaveMemberResponse;
import portfolio.beom.repository.MemberRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    //todo 인터페이스 만들기.

    private final MemberRepository memberRepository;
    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
        //todo 비밀번호 암호화
        Member member = Member.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();

        Member savedMember = memberRepository.save(member);

        return new SaveMemberResponse(savedMember);
    }
    @Transactional
    public LoginMemberResponse login(LoginMemberRequest request) {
        Member loginMember = memberRepository.findByName(request.getName()).orElseThrow(IllegalArgumentException::new);

        if (request.getPassword().equals(loginMember.getPassword())) {
            return new LoginMemberResponse(loginMember);
        }
        return null;
    }
}
