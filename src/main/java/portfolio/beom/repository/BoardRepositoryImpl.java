package portfolio.beom.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import portfolio.beom.dto.request.BoardSearchCond;
import portfolio.beom.dto.response.WriteBoardResponse;

import java.util.List;

import static portfolio.beom.domain.board.QBoard.board;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<WriteBoardResponse> findBoardByTitle(BoardSearchCond searchRequest) {
        List<WriteBoardResponse> content = jpaQueryFactory
                .select(Projections.constructor(WriteBoardResponse.class,
                        board))
                .from(board)
                .where(likeTitle(searchRequest.getLikeTitle()))
                .offset(searchRequest.getOffset())
                .limit(searchRequest.getSize())
                .orderBy(boardOrderBy(searchRequest))
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(board.id.count())
                .from(board)
                .where(likeTitle(searchRequest.getLikeTitle()));

        PageRequest pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), searchRequest.getSort());

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression likeTitle(String title) {
        return title != null ? board.title.like("%" + title + "%") : null;
    }

    private OrderSpecifier<?> boardOrderBy(BoardSearchCond searchCond) {
        if (!searchCond.getSort().isEmpty()) {
            for (Sort.Order order : searchCond.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "content": {
                        return new OrderSpecifier<>(direction, board.content).nullsLast();
                    }
                    case "title": {
                        return new OrderSpecifier<>(direction, board.title).nullsLast();
                    }
                    case "writer": {
                        return new OrderSpecifier<>(direction, board.writer).nullsLast();
                    }
                    case "id": {
                        return new OrderSpecifier<>(direction, board.id).nullsLast();
                    }
                }
            }
        }
        return null;
    }
}
