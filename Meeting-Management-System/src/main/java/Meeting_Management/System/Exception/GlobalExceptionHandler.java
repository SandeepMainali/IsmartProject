package Meeting_Management.System.Exception;

import Meeting_Management.System.Dto.ResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException ex){
        StringBuilder errorMessages = new StringBuilder();
        BindingResult result = ex.getBindingResult();
        result.getAllErrors().forEach(error -> {
            errorMessages.append(error.getDefaultMessage()).append("/n");
        });
        logger.error(errorMessages.toString());
        ResponseDTO responseDTO = new ResponseDTO("Error","E001",errorMessages.toString(),null,null);
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDTO> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        logger.error("HTTP Method Not Allowed: {}", ex.getMessage());
        ResponseDTO responseDTO = new ResponseDTO("Error", "E002", "Method Not Allowed for this URL: " + request.getRequestURL().toString(), null, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleMalformedJson(HttpMessageNotReadableException ex){
        logger.error(ex.getMessage());
        ResponseDTO responseDTO = new ResponseDTO("Error","E003","Malformed Json Data or Invalid input data type: Please check your input and try again.",null,null);
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseDTO> handleNoHandlerFound(NoHandlerFoundException ex) {
        logger.error(ex.getMessage());
        ResponseDTO responseDTO = new ResponseDTO("Error","E004","No handler found for: "+ex.getRequestURL(),null,null);
        return new ResponseEntity<>(responseDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        logger.error(ex.getMessage());
        ResponseDTO responseDTO = new ResponseDTO("Error", "E005","Entity Not Found!!",null,null);
        return new ResponseEntity<>(responseDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        logger.error(ex.getMessage());
        ResponseDTO responseDTO = new ResponseDTO("Error","E006","Database Constraint Violated!!",null,null);
        return new ResponseEntity<>(responseDTO,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseDTO> handleServiceException(ServiceException ex) {
        logger.error(ex.getMessage());
        ResponseDTO errorResponse = new ResponseDTO("Error", "E007", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse,ex.getStatus());
    }
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseEntity<ResponseDTO> handleDatabaseError(Exception ex) {
        logger.error(ex.getMessage());
        ResponseDTO responseDTO = new ResponseDTO("Error","E008","Database error!!", null,null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: " + ex.getMessage());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
//                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
//        );
//        return ResponseEntity.badRequest().body(errors);
//    }
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ResponseDTO> handleInvalidFormat(HttpMessageNotReadableException ex) {
//        logger.error(ex.getMessage());
//        ResponseDTO errorResponse = new ResponseDTO("Error", "E0000","Invalid data provided: Please check your input and try again!!", null, null);
//        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneral(Exception ex) {
        logger.error(ex.getMessage());
        ResponseDTO errorResponse = new ResponseDTO("Error", "E009","Server Error: Please try again!!", null, null);
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
