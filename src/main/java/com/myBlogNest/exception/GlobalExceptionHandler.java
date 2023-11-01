package com.myBlogNest.exception;

import com.myBlogNest.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Handle ResourceNotFoundException and return a custom error response
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest) {
        // Create an ErrorDetails object with timestamp, error message, and request description
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
        // Return the custom error response with HTTP status code 404 (Not Found)
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }
}
    // You can add more exception handlers for other exception types if needed
    // For example, you can uncomment the following code and add exception handlers for other exceptions:


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception e, WebRequest webRequest){
// // Create an ErrorDetails object with timestamp, error message, and request description
//
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
//// Return the custom error response with HTTP status code 500 (Internal Server Error)
//        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);


    // }

    // More exception handlers can be added as needed for different exception types.

