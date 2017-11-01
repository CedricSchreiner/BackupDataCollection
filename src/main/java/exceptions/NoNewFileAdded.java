package exceptions;

public class NoNewFileAdded extends RuntimeException{
    public NoNewFileAdded(String iv_message) {
        super(iv_message);
    }
}
