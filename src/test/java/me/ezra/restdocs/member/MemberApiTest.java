package me.ezra.restdocs.member;

import me.ezra.restdocs.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Snippet;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MemberApiTest extends TestSupport {

    @Test
    public void member_get_all() throws Exception {
        Snippet requestParameters;
        mvc.perform(get("/api/members")
                        .param("size", "10")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestParameters(
                                parameterWithName("size").optional().description(
                                        "size"),
                                parameterWithName("page").optional().description("page")
                        )
                ));
    }

    @Test
    public void member_get() throws Exception {
        mvc.perform(get("/api/members/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("id").description("member id")
                        ),
                        responseFields(
                                fieldWithPath("name").description("name"),
                                fieldWithPath("email").description("email")
                        )
                ));
    }

    @Test
    public void member_create() throws Exception {
        mvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("/json/member-api/create.json"))
                )
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("name").description("name"),
                                fieldWithPath("email").description("email")
                        )
                ));
    }

    @Test
    public void member_update() throws Exception {
        mvc.perform(put("/api/members/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("/json/member-api/update.json"))
                )
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("id").description("member id")
                        ),
                        requestFields(
                                fieldWithPath("name").description("name")
                        )
                ));
    }


}