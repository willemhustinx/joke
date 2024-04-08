package nl.sogeti.java.joke.exception;

public class NoJokeException extends RuntimeException {
    public NoJokeException(String errorMessage) {
        super(errorMessage);
    }
}
