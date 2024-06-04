package dev.mvc.team4;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.community.Community;
import dev.mvc.reply.Reply;
import dev.mvc.singo.Singo;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/trash/storage/**").addResourceLocations("classpath:/static/images/trash/storage");

        registry.addResourceHandler("/replys/storage/**").addResourceLocations("file:///" + Reply.getUploadDir());

        registry.addResourceHandler("/i/Desktop/teamfile/**")
                .addResourceLocations("file:///" + Community.getUploadDir());

        registry.addResourceHandler("/Desktop/teamfile/**").addResourceLocations("file:///" + Singo.getUploadDir());

    }
}
