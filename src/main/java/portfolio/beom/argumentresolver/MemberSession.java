package portfolio.beom.argumentresolver;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.beom.domain.member.Member;

@NoArgsConstructor
@Data
public class MemberSession {

    private Long memberId;
    private String email;

    @Builder
    public MemberSession(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }

    public MemberSession(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
    }
}
