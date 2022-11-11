package me.ezra.restdocs.member;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class MemberSignUpRequest {

    @Email
    private String email;

    @NotNull
    private String name;

    public Member toEntity() {
        return new Member(email, name);
    }
}
