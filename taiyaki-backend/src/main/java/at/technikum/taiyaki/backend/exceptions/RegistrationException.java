package at.technikum.taiyaki.backend.exceptions;

public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super(message);
    }
}