package main.third.exception;

public class InvalidRequestBody extends Exception {
    public InvalidRequestBody(String message) {
        super(message);
    }
}
