package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIFS;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIFS{

    private final ErrorCodeIFS errorCodeIFS;

    private final String errorDescription;

    public ApiException(ErrorCodeIFS errorCodeIFS){
        super(errorCodeIFS.getDescription());
        this.errorCodeIFS = errorCodeIFS;
        this.errorDescription = errorCodeIFS.getDescription();
    }
    public ApiException(ErrorCodeIFS errorCodeIFS, String errorDescription){
        super(errorDescription);
        this.errorCodeIFS = errorCodeIFS;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeIFS errorCodeIFS, Throwable thr){
        super(thr);
        this.errorCodeIFS = errorCodeIFS;
        this.errorDescription = errorCodeIFS.getDescription();
    }

    public ApiException(ErrorCodeIFS errorCodeIFS, Throwable thr, String errorDescription){
        super(thr);
        this.errorCodeIFS = errorCodeIFS;
        this.errorDescription = errorDescription;
    }
}
