package portfolio.beom.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.beom.domain.member.Member;

@NoArgsConstructor
@Data
public class LoginMemberResponse {

    private Long id;
    private String name;
    private String email;

    public LoginMemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
