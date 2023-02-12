package portfolio.beom.exadvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import portfolio.beom.controller.MemberController;

import java.util.HashMap;
import java.util.List;


//todo 패키지 네임 변경 -> ex. exception,
@RestControllerAdvice(basePackageClasses = MemberController.class)
public class ExRestControllerAdvice {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e) {
//        return new ErrorResult("BAD", e.getMessage());
//    }

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

//    @ExceptionHandler
//    public ResponseEntity<ErrorResult> invalidMemberException(InvalidMemberException e) {
//        ErrorResult body = new ErrorResult("BAD", e.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }
}
