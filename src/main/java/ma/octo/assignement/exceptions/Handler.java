package ma.octo.assignement.exceptions;

import ma.octo.assignement.response.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(SoldeDisponibleInsuffisantException.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(SoldeDisponibleInsuffisantException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Object> handleTransactionException(TransactionException ex, WebRequest request) {
        Result<Void> result =new Result<>();
        result.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(result, null, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CompteNonExistantException.class)
    public ResponseEntity<Object> handleCompteNonExistantException(CompteNonExistantException ex, WebRequest request) {

        Result<Void> result =new Result<>();
        result.addErrorMessage(ex.getMessage());
        return new ResponseEntity<>(result, null, HttpStatus.UNAUTHORIZED);
    }

    /*
    Handle Validation Exception
    */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Result<Void> result =new Result<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                        result.addErrorMessage(error.getDefaultMessage()));
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
