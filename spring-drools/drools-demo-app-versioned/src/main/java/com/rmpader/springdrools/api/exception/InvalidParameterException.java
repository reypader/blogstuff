package com.rmpader.springdrools.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author RMPader
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,
                reason = "There was a problem parsing the parameters provided. Please review the API Documentation.")
public class InvalidParameterException extends RuntimeException {

}
