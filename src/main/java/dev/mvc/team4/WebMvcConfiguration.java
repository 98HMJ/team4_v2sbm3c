package dev.mvc.team4;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.community.Community;
import dev.mvc.reply.Reply;
import dev.mvc.singo.Singo;
import dev.mvc.trash_exploration.Exploration;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/replys/storage/**").addResourceLocations("file:///" + Reply.getUploadDir());
        
        registry.addResourceHandler("/i/Desktop/teamfile/**").addResourceLocations("classpath:/images/test/");
        
        registry.addResourceHandler("/Desktop/teamfile/**").addResourceLocations("file:///" + Singo.getUploadDir());

        // Exploration 이미지 디렉토리 핸들러
        registry.addResourceHandler("/images/trash_exploration/storage/**")
                .addResourceLocations("file:///" + Exploration.getUploadDir());
    }
}
