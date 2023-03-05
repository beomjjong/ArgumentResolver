package portfolio.beom.exception.member;

import portfolio.beom.exception.BeomException;

public class FailedUpdateMemberException extends BeomException {

    private final static String MESSAGE = "업데이트 실패 했습니다.";
    public FailedUpdateMemberException() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
