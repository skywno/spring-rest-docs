package me.ezra.restdocs.member;

import me.ezra.restdocs.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MemberApiTest extends TestSupport {

    @Test
    public void member_get_all() throws Exception {
        mvc.perform(get("/api/members")
                        .param("size", "10")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void member_get() throws Exception {
        mvc.perform(get("/api/members/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void member_create() throws Exception {
        mvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("/json/member-api/create.json"))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void member_update() throws Exception {
        mvc.perform(put("/api/members/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("/json/member-api/update.json"))
                )
                .andExpect(status().isOk());
    }


}