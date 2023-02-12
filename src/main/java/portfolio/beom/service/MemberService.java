package portfolio.beom.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.beom.domain.member.Member;
import portfolio.beom.domain.member.request.LoginMemberRequest;
import portfolio.beom.domain.member.request.SaveMemberRequest;
import portfolio.beom.domain.member.response.LoginMemberResponse;
import portfolio.beom.domain.member.response.SaveMemberResponse;
import portfolio.beom.repository.MemberRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
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
