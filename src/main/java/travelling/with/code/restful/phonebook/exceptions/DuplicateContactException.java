package travelling.with.code.restful.phonebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Contact with this id already exists.")
public class DuplicateContactException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateContactException(String contactId) {
        super("Contact with id '" + contactId + "' already exists.");
    }

}
