package me.ezra.restdocs.admin;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminPasswordModificationRequest {

    @Size(min=5, max=20)
    @NotNull
    private String password;

    @Builder
    public AdminPasswordModificationRequest(String password) {
        Assert.hasText(password, "password must not be null");

        this.password = password;
    }
}
