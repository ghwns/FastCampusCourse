package org.delivery.api.common.error;

public interface ErrorCodeIFS {
    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();
}
