package portfolio.beom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.beom.argumentresolver.LoginUser;
import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.dto.request.WriteBoardRequest;
import portfolio.beom.dto.response.WriteBoardResponse;
import portfolio.beom.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<WriteBoardResponse> write(@LoginUser MemberSession memberSession,
                                                    @RequestBody @Valid WriteBoardRequest writeBoardRequest) {
        WriteBoardResponse writeBoard = boardService.save(writeBoardRequest, memberSession);
        return new ResponseEntity<>(writeBoard, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WriteBoardResponse> getPost(@PathVariable Long id) {
       WriteBoardResponse response = boardService.getPost(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WriteBoardResponse>> getPostAll() {
        List<WriteBoardResponse> posts = boardService.getPostAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

}
