package platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchCodeException extends RuntimeException {

    public NoSuchCodeException() {
    }

    public NoSuchCodeException(String message) {
        super(message);
    }
}
