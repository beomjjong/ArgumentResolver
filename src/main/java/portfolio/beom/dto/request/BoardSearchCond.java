package portfolio.beom.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardSearchCond extends SearchRequest{

    private String likeTitle;
}
