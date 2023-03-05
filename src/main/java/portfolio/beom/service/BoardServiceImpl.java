package portfolio.beom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.domain.board.Board;
import portfolio.beom.dto.request.BoardSearchCond;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;
import portfolio.beom.repository.BoardRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public WriteBoardResponse save(WriteBoardRequest writeBoardRequest, MemberSession memberSession) {

        Board board = Board.builder()
                .writer(memberSession.getName())
                .title(writeBoardRequest.getTitle())
                .content(writeBoardRequest.getContent())
                .createDate(LocalDateTime.now())
                .build();

        Board savedWrite = boardRepository.save(board);

        return new WriteBoardResponse(savedWrite);
    }

    @Override
    public WriteBoardResponse getPost(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return new WriteBoardResponse(board);
    }

    @Override
    public Page<WriteBoardResponse> getPostAll(BoardSearchCond searchRequest) {
        return boardRepository.findBoardByTitle(searchRequest);
    }
}
