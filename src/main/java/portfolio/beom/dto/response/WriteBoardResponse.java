package portfolio.beom.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.beom.domain.board.Board;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class WriteBoardResponse {

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createDate = LocalDateTime.now();


    public WriteBoardResponse(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.createDate = board.getCreateDate();
    }
}
