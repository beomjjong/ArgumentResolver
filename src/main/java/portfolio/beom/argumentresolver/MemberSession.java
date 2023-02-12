package portfolio.beom.argumentresolver;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
