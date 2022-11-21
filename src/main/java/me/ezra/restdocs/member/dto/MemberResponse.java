package me.ezra.restdocs.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.ezra.restdocs.admin.Admin;
import me.ezra.restdocs.member.domain.Member;

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
