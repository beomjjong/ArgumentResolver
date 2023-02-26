package portfolio.beom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;


@RestControllerAdvice(basePackages = "portfolio.beom.controller")
public class ExRestControllerAdvice {


    @ExceptionHandler
    public ResponseEntity<ErrorResult> exHandler(MethodArgumentNotValidException e) {
        HashMap<Object, Object> result = new HashMap<>();

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String message = null;

        for (FieldError fieldError : fieldErrors) {
            result.put("틀린곳 " + fieldError.getField(), fieldError.getRejectedValue());
            message = fieldError.getDefaultMessage();
        }
        ErrorResult body = new ErrorResult("EX", message, result);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> invalidMemberException(BeomException e) {
        ErrorResult errorResult = new ErrorResult("EX", e.getMessage(), null);
        return new ResponseEntity<>(errorResult, HttpStatus.valueOf(e.statusCode()));
    }
}
