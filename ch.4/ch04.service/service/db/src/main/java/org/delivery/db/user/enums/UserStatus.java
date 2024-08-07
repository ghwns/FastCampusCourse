package org.delivery.db.user.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    REGISTERED("등록"),

    UNREGISTERD("해지"),

    ;

    private final String description;

}
