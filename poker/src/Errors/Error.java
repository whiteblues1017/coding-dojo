package Errors;

public class Error {

    public static class InputException extends Exception {
        public InputException(String msg) {
            super(msg);
        }
    }

    public static class DuplicateException extends Exception {
        public DuplicateException(String msg) {
            super(msg);
        }
    }

}
