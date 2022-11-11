package me.ezra.restdocs.member;

import me.ezra.restdocs.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class MemberApiTest extends TestSupport {

    @Autowired
    protected MockMvc mvc;

    @Test
    public void member_get_all() throws Exception {
        mvc.perform(get("/api/members")
                        .param("size", "10")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andDo(document("{class-name}/{method-name}"))
                .andExpect(status().isOk());
    }

    @Test
    public void member_get() throws Exception {
        mvc.perform(get("/api/members/{id}", 1L)
                        .param("size", "10")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andDo(document("{class-name}/{method-name}"))
                .andExpect(status().isOk());
    }

    @Test
    public void member_create() throws Exception {
        mvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("/json/member-api/create.json"))
                ).andDo(print())
                .andDo(document("{class-name}/{method-name}"))
                .andExpect(status().isOk());
    }

    @Test
    public void member_update() throws Exception {
        mvc.perform(put("/api/members/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("/json/member-api/update.json"))
                ).andDo(print())
                .andDo(document("{class-name}/{method-name}"))
                .andExpect(status().isOk());
    }


}