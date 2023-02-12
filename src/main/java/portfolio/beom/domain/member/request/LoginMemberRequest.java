package portfolio.beom.domain.member.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginMemberRequest {

    private String name;
    private String password;

}
