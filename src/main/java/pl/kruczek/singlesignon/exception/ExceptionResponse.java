package pl.kruczek.singlesignon.exception;

import lombok.Builder;

@Builder
public class ExceptionResponse {
    protected Integer status;
    protected String message;
}
