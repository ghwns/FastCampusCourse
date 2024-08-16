package org.delivery.api.common.exception;

import org.delivery.api.common.error.ErrorCodeIFS;

public interface ApiExceptionIFS {
    ErrorCodeIFS getErrorCodeIFS();

    String getErrorDescription();
}
