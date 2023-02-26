package portfolio.beom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.beom.argumentresolver.SessionConst;
import portfolio.beom.domain.board.Board;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;
import portfolio.beom.repository.BoardRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public WriteBoardResponse save(WriteBoardRequest writeBoardRequest) {
        Board board = Board.builder()
                .writer(SessionConst.LOGIN_MEMBER)
                .title(writeBoardRequest.getTitle())
                .content(writeBoardRequest.getContent())
                .createDate(LocalDateTime.now())
                .build();

        Board savedWrite = (Board) boardRepository.save(board);

        return new WriteBoardResponse(savedWrite);
    }
}
