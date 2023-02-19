package portfolio.beom.exadvice;

public abstract class BeomException extends RuntimeException {

    public BeomException(String message) {
        super(message);
    }

    public abstract int statusCode();
}
