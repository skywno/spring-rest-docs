package me.ezra.restdocs.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponse {
    private final String email;
    private final String name;

    public MemberResponse(final Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }
}
