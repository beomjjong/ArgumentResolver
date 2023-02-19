package portfolio.beom.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateMemberRequest {

    private String name;

    private String password;

    private String email;
}
