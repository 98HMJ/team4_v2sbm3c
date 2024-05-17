package dev.mvc.team4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.mvc.tool.MailTool;

@Configuration
public class MailConfig {
    @Bean
    MailTool mailTool(){
        return new MailTool();
    }
}
