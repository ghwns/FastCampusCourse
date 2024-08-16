package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeIFS;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;

    public static Result OK(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIFS errorCodeIFS){
        return Result.builder()
                .resultCode(errorCodeIFS.getErrorCode())
                .resultMessage(errorCodeIFS.getDescription())
                .resultDescription("에러 발생")
                .build();
    }

    public static Result ERROR(ErrorCodeIFS errorCodeIFS, Throwable thr){
        return Result.builder()
                .resultCode(errorCodeIFS.getErrorCode())
                .resultMessage(errorCodeIFS.getDescription())
                .resultDescription(thr.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeIFS errorCodeIFS, String description){
        return Result.builder()
                .resultCode(errorCodeIFS.getErrorCode())
                .resultMessage(errorCodeIFS.getDescription())
                .resultDescription(description)
                .build();
    }
}
