package portfolio.beom.service;

import org.springframework.data.domain.Page;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.dto.request.BoardSearchCond;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;

import java.util.List;

public interface BoardService {
    WriteBoardResponse save(WriteBoardRequest writeBoardRequest, MemberSession memberSession);

    WriteBoardResponse getPost(Long id);

    Page<WriteBoardResponse> getPostAll(BoardSearchCond searchRequest);

}
