package me.ezra.restdocs.member.dto;

import lombok.Getter;
import me.ezra.restdocs.member.domain.Member;

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
