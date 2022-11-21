package me.ezra.restdocs.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
public class AdminSignUpRequest {
    @Email
    private String email;

    @NotNull
    private String name;

    @Size(min=3, max=20)
    private String password;

    @Builder
    public AdminSignUpRequest(String email, String name, String password) {
        Assert.hasText(email, "Email must not be null");
        Assert.hasText(name, "name must not be null");
        Assert.hasText(password, "password must not be null");

        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Admin toEntity() {return new Admin(email, name, password);}
}
