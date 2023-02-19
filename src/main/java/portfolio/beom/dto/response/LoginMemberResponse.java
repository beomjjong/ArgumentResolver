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


    //파라미터 값에 MemberSession 을 넣어야 하는지 물어보기
    public LoginMemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
