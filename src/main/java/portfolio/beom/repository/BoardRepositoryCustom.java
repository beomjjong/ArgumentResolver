package portfolio.beom.repository;

import org.springframework.data.domain.Page;
import portfolio.beom.dto.request.BoardSearchCond;
import portfolio.beom.dto.response.WriteBoardResponse;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<WriteBoardResponse> findBoardByTitle(BoardSearchCond searchRequest);
}
