package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIFS;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){
        var api = new Api<T>();
        api.result = Result.OK();
        api.body = data;
        return api;
    }

    public static Api<Object> ERROR(Result result){
        var api = new Api<Object>();
        api.result = result;
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIFS errorCodeIFS){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIFS);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIFS errorCodeIFS, Throwable thr){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIFS, thr);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIFS errorCodeIFS, String description){
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIFS, description);
        return api;
    }
}
