package me.ezra.restdocs.member.dto;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class MemberModificationRequest {

    @NotNull
    private String name;
}
