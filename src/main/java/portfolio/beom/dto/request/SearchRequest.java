package portfolio.beom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;


import static java.lang.Math.max;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchRequest {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 2;

    @Builder.Default
    private Sort.Direction direction = DESC;
    private String sortBy;

    public long getOffset() {
        return (long) (max(1, page) - 1) * size;
    }

    public int getSize() {
        return size >= MAX_SIZE ? MAX_SIZE : size;
    }

    public Sort getSort() {
        return Sort.by(getDirection(), getSortBy());
    }

}
