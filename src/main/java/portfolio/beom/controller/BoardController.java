package portfolio.beom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import portfolio.beom.argumentresolver.LoginUser;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;
import portfolio.beom.service.BoardService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BoardController {
// 게시글 작성. 본인이 로그인을 했으니까 게시글 작성은 제목 내용만 적으면 작성이 되고, 게시글 조회를 했을 때 제목 내용 작성자 가 표시 되어야 한다.

    private final BoardService boardService;

    @PostMapping("/api/posts")
    public ResponseEntity<WriteBoardResponse> write(@LoginUser MemberSession memberSession,
                                                    @RequestBody @Valid WriteBoardRequest writeBoardRequest) {
        WriteBoardResponse writeBoard = boardService.save(writeBoardRequest, memberSession);
        return new ResponseEntity<>(writeBoard, HttpStatus.CREATED);
    }

}
