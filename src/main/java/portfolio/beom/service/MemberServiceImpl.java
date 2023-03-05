package portfolio.beom.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.domain.member.Member;
import portfolio.beom.dto.request.LoginMemberRequest;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.request.UpdateMemberRequest;
import portfolio.beom.dto.response.LoginMemberResponse;
import portfolio.beom.dto.response.SaveMemberResponse;
import portfolio.beom.dto.response.UpdateMemberResponse;
import portfolio.beom.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public SaveMemberResponse save(SaveMemberRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .password(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt())) //암호화 완료
                .email(request.getEmail())
                .build();

        Member savedMember = memberRepository.save(member);

        return new SaveMemberResponse(savedMember);
    }

    @Override
    @Transactional
    public LoginMemberResponse login(LoginMemberRequest request) {
        Member loginMember = memberRepository.findByName(request.getName()).orElseThrow(IllegalArgumentException::new);

        if (BCrypt.checkpw(request.getPassword(), loginMember.getPassword())) {
            return new LoginMemberResponse(loginMember);
        }
        return null;

    }

    @Override
    @Transactional
    public UpdateMemberResponse update(Long memberId, UpdateMemberRequest request) {
        Member updatedMember = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        updatedMember.update(request.getName(), BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()), request.getEmail());

        return new UpdateMemberResponse(updatedMember);
    }

    @Override
    public MemberSession getUser(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);

        return new MemberSession(member);
    }

    @Override
    @Transactional
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        memberRepository.delete(member);
    }
}
