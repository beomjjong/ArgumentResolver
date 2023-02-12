package portfolio.beom.domain.member.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SaveMemberRequest {

    private String name;

    private String password;

    private String email;
}
