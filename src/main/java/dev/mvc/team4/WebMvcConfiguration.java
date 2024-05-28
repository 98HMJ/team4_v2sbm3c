package dev.mvc.team4;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.community.Community;
import dev.mvc.reply.Reply;
import dev.mvc.singo.Singo;
import dev.mvc.trash.Trash;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Windows: path = "C:/kd/deploy/resort_v2sbm3c_blog/contents/storage";
        // ▶ file:///C:/kd/deploy/resort_v2sbm3c_blog/contents/storage
      
        // Ubuntu: path = "/home/ubuntu/deploy/resort_v2sbm3c_blog/contents/storage";
        // ▶ file:////home/ubuntu/deploy/resort_v2sbm3c_blog/contents/storage
      
        // JSP 인식되는 경로: http://localhost:9091/contents/storage";
        registry.addResourceHandler("/trash/storage/**").addResourceLocations("file:///" +  Trash.getUploadDir());
        
        registry.addResourceHandler("/replys/storage/**").addResourceLocations("file:///" +  Reply.getUploadDir());
        
        registry.addResourceHandler("/i/Desktop/teamfile/**").addResourceLocations("file:///" +  Community.getUploadDir());
        
        registry.addResourceHandler("/Desktop/teamfile/**").addResourceLocations("file:///" +  Singo.getUploadDir());
        // JSP 인식되는 경로: http://localhost:9091/attachfile/storage";
        // registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
        
        // JSP 인식되는 경로: http://localhost:9091/member/storage";
        // registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");
        
    }
}
