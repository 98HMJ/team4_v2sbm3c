package dev.mvc.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Component
public class MyAppProperties {

    @Value("${admin.email}")
    private String email;

    @Value("${admin.key}")
    private String key;

    @Value("${secret.code}")
    private String code;
}