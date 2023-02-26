package portfolio.beom.exception;

public class InvalidMemberException extends BeomException {

    private static final String MESSAGE = "로그인된 회원이 아닙니다.";

    public InvalidMemberException() {
        super(MESSAGE);
    }


    @Override
    public int statusCode() {
        return 400;
    }
}
