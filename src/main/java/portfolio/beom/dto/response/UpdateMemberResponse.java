package portfolio.beom.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.beom.domain.member.Member;

@Data
@NoArgsConstructor
public class UpdateMemberResponse {

    private Long id;

    private String name;

    private String password;

    private String email;

    public UpdateMemberResponse(Member updatedMember) {
        this.id = updatedMember.getId();
        this.name = updatedMember.getName();
        this.password = updatedMember.getPassword();
        this.email = updatedMember.getEmail();
    }
}
