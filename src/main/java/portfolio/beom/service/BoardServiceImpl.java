package portfolio.beom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.domain.board.Board;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;
import portfolio.beom.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

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

    @Transactional
    @Override
    public WriteBoardResponse getPost(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return new WriteBoardResponse(board);
    }

    @Transactional
    @Override
    public List<WriteBoardResponse> getPostAll() {
        List<Board> posts = boardRepository.findAll();

        List<WriteBoardResponse> list = new ArrayList<>();

        for (Board board : posts) {
            WriteBoardResponse response = new WriteBoardResponse(board);
            list.add(response);
        }
        return list;
    }
}
