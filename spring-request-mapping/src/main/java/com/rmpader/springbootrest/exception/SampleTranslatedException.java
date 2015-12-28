package com.rmpader.springbootrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author RMPader
 */
@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED,
                reason = "A sample annotated exception.")
public class SampleTranslatedException extends RuntimeException {

}
