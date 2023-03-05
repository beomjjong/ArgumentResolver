package portfolio.beom.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class WriteBoardRequest {

    @NotBlank(message = "제목을 작성해주세요.")
    private String title;

    @Lob
    @NotBlank(message = "내용을 작성해주세요.")
    private String content;
}
