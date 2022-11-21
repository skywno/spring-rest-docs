package me.ezra.restdocs.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ezra.restdocs.TestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminApiTest extends TestSupport {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void reset() {
        Admin admin = adminRepository.findById(1L).get();
        admin.updatePassword("password");
        adminRepository.save(admin);
    }

    @Test
    @DisplayName("[GET] All Admins")
    void givenNothing_whenRequestingEveryAdminInfo() throws Exception {
        /// Give

        // When & Then
        mvc.perform(RestDocumentationRequestBuilders.get("/api/admins")
                        .queryParam("size", "10")
                        .queryParam("page", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(restDocs.document(
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("size").optional().description("size"),
                                RequestDocumentation.parameterWithName("page").optional().description("page")
                        )
                ));
    }

    @DisplayName("[GET] Admin")
    @Test
    void givenAdminId_whenRequestingAdminInfo() throws Exception {
        // Given
        long adminId = 1L;

        // When & Then
        mvc.perform(RestDocumentationRequestBuilders.get("/api/admins/{id}", adminId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"email\":\"admin@gmail.com\"," +
                        "\"name\":\"ezra lim\",\"password\":\"password\"}"))
                .andDo(restDocs.document(
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("Admin id")
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("email").description("email"),
                                        PayloadDocumentation.fieldWithPath("name").description("name"),
                                        PayloadDocumentation.fieldWithPath("password").description("password")
                                )
                        )
                );
    }

    @DisplayName("[POST] Admin")
    @Test
    void givenSignUpRequest_whenCreatingAdmin() throws Exception {
        // Given
        AdminSignUpRequest req = AdminSignUpRequest.builder()
                .email("test@test.com")
                .name("test admin")
                .password("password")
                .build();
        // When & Then
        mvc.perform(post("/api/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("name").description("name"),
                                PayloadDocumentation.fieldWithPath("email").description("email"),
                                PayloadDocumentation.fieldWithPath("password").description("password")
                        )
                ));
    }

    @DisplayName("[PUT] Admin")
    @Test
    void givenAdminIdAndModificationRequest_whenUpdatingAdmin() throws Exception {
        // Given
        long adminId = 1L;
        // When & Then
        mvc.perform(put("/api/admins/{id}", adminId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"new_password\"}")
                ).andExpect(status().isOk())
                .andDo(restDocs.document(
                        RequestDocumentation.pathParameters(
                                parameterWithName("id").description("member id")
                        ),
                        PayloadDocumentation.requestFields(
                                fieldWithPath("password").description("password")
                        )
                ));
        ;
    }

}