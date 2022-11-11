package me.ezra.restdocs.member;


import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MemberModificationRequest {

    @NotNull
    private String name;
}
