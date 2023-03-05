package portfolio.beom.service;

import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;

import java.util.List;

public interface BoardService {
    WriteBoardResponse save(WriteBoardRequest writeBoardRequest, MemberSession memberSession);

    WriteBoardResponse getPost(Long id);

    List<WriteBoardResponse> getPostAll();

}
