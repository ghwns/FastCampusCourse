package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountRepository accountRepository;
    @GetMapping("/me")
    public Api<AccountMeResponse> me() {
        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("hong@gil.dong")
                .registeredAt(LocalDateTime.now())
                .build();

        var str = "Hello";
        var age = 0;
       try{
           age = Integer.parseInt(str);
       }catch (Exception e){
           throw new ApiException(ErrorCode.SERVER_ERROR, e, "사용자 Me 호출시 에러");
       }

        return Api.OK(response);
//        return Api.ERROR(UserErrorCode.USER_NOT_FOUND, "해당 사용자 없음");
    }
}
