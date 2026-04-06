package finance_dashboard.exception;


import finance_dashboard.dto.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandling.class) ;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFound(ResourceNotFoundException exception){
        ResponseMessage message = new ResponseMessage();
        message.setStatus(false);
        message.setMessage(exception.getMessage());
        return  new ResponseEntity<>(message, HttpStatus.NOT_FOUND) ;
    }


//    public


    //MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, Object> response = new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field, message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ResponseMessage> handleBadApiRequest(BadApiRequestException ex) {
        logger.info("Bad api request");
        ResponseMessage response = new ResponseMessage() ;
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

//}

}
