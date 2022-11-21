package me.ezra.restdocs.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ezra.restdocs.TestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestDocument extends TestSupport {

    private final ObjectMapper objectMapper;


    @Autowired
    public RestDocument(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Test
    void sampleRequest() throws Exception {
        RestDocumentApi.SampleRequest sampleRequest = new RestDocumentApi.SampleRequest("name", "email");
        mvc.perform(post("/test/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleRequest))
        )
                .andExpect(status().isBadRequest())
                .andDo(
                        restDocs.document(
                                responseFields(
                                        fieldWithPath("message").description("에러 메세지"),
                                        fieldWithPath("status").description("Http Status Code"),
                                        fieldWithPath("code").description("Error Code"),
                                        fieldWithPath("timestamp").description("에러 발생 시간"),
                                        fieldWithPath("errors").description("Error 값 배열 값"),
                                        fieldWithPath("errors[0].field").description("문제 있는 필드"),
                                        fieldWithPath("errors[0].value").description("문제가 있는 값"),
                                        fieldWithPath("errors[0].reason").description("문제가 있는 이유")
                                )
                        )
                );
    }
}
