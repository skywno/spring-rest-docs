package me.ezra.restdocs.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.ezra.restdocs.member.domain.Member;

@AllArgsConstructor
@Getter
public class AdminResponse {
    private final String email;
    private final String name;
    private final String password;

    public AdminResponse(final Admin admin) {
        this.email = admin.getEmail();
        this.name = admin.getName();
        this.password = admin.getPassword();
    }
}
