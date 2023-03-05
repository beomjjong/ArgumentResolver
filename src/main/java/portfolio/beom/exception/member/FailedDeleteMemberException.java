package portfolio.beom.exception.member;

import portfolio.beom.exception.BeomException;

public class FailedDeleteMemberException extends BeomException {

    private final static String MESSAGE = "회원 삭제 실패했습니다.";

    public FailedDeleteMemberException() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
