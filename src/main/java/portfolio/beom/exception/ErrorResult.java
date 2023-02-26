package portfolio.beom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
    private Map validate;
}
