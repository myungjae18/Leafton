package idusw.leafton.model;


import groovy.util.logging.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    String mainPath = "/passion/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")//정적 파일 찾는 표지판
                .addResourceLocations("file:C:\\images\\");//실제 정적 리소스 위치
    }
}