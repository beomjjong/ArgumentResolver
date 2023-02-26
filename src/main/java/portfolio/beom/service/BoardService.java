package portfolio.beom.service;

import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;

public interface BoardService {
    WriteBoardResponse save(WriteBoardRequest writeBoardRequest);
}
