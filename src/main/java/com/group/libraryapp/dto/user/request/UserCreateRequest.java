package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age;    // int는 널을 표현할수 없으므로 객체 자료형인 Integer 사용

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
