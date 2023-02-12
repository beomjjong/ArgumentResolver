package portfolio.beom.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class SaveMemberRequest {

    //todo 최소한의 검증은 하기.

    @Size(min = 5, max = 20, message = "길게 써주세요.")
    private String name;

    @Size(min = 5, max = 20, message = "min 5 t0 max 20 plz")
    private String password;

    private String email;
}
