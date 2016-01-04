package com.rmpader.springdrools.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author RMPader
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,
                reason = "Artifact does not exist.")
public class InvalidArtifactException extends RuntimeException {

}
