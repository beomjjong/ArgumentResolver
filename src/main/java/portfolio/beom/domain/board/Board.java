package portfolio.beom.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    private String title;

    private String content;

    private LocalDateTime createDate = LocalDateTime.now();


    @Builder
    public Board(String writer, String title, String content, LocalDateTime createDate) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }
}
