package portfolio.beom.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginMemberRequest {

    private String name;
    private String password;

}
