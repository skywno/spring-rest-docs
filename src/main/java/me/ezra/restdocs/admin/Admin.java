package me.ezra.restdocs.admin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 5, max=20)
    @Column(name = "password", nullable = false)
    private String password;


    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false, updatable = false)
    private LocalDateTime modifiedAt;


    public Admin(final String email, final String name, final String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void updatePassword(final String password) {
        this.password = password;
    }

    public String toString() {
        return "Admin(id=" + this.getId() + ", email=" + this.getEmail() + ", name=" + this.getName() + ", password=" + this.getPassword() + ", createdAt=" + this.getCreatedAt() + ", modifiedAt=" + this.getModifiedAt() + ")";
    }
}
