package travelling.with.code.restful.phonebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class IllegalContactException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalContactException(String message) {
        super(message);
    }

}
