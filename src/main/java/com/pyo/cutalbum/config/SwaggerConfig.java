package com.pyo.cutalbum.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .info(new Info().title("4cus API")
                        .description("4cus API 명세서 입니다. 수정할게 있다면 알려주세요. 폼데이터로 데이터 전송하셔야 됩니다. " +
                                "사진 저장 오래걸립니다. 연타 X 프론트에서 연타 안되도록 처리해주시면 감사하겠습니다.")
                        .version("1.0").contact(new Contact().name("BLUE")));
    }


}