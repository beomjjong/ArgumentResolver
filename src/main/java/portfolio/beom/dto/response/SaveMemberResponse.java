package portfolio.beom.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.beom.domain.member.Member;

@NoArgsConstructor
@Data
public class SaveMemberResponse {

    private String name;

    private String email;

    public SaveMemberResponse(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
