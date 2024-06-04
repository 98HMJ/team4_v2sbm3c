package dev.mvc.team4;

import java.io.IOException;
import java.net.URLDecoder;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import dev.mvc.community.Community;
import dev.mvc.reply.Reply;
import dev.mvc.singo.Singo;
import dev.mvc.trash.Trash;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/trash/storage/**").addResourceLocations("classpath:/static/images/trash/storage");

        registry.addResourceHandler("/replys/storage/**").addResourceLocations("file:///" + Reply.getUploadDir());

        registry.addResourceHandler("/i/Desktop/teamfile/**")
                .addResourceLocations("file:///" + Community.getUploadDir());

        registry.addResourceHandler("/Desktop/teamfile/**").addResourceLocations("file:///" + Singo.getUploadDir());

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, "UTF-8");
                        return super.getResource(resourcePath, location);
                    }
                });

    }
}
