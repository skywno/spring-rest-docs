package me.ezra.restdocs;

import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
@Import(RestDocsConfiguration.class)
public class TestSupport {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void setUp(final WebApplicationContext context,
               @Autowired final RestDocumentationContextProvider provider
    ) {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .alwaysDo(print())
                .alwaysDo(restDocs)
                .build();
    }

    protected String readJson(final String path) throws IOException {
        return IOUtils.toString(
                resourceLoader.getResource("classpath:" + path).getInputStream(),
                StandardCharsets.UTF_8
        );
    }
}
