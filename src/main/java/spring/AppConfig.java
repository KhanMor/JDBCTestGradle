package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by Mordr on 02.03.2017.
 */
@Configuration
@ComponentScan({"services.servicesimpl", "models.dao", "controllers.mvc"})
public class AppConfig {
    @Bean
    public UrlBasedViewResolver GetResolver(){
        UrlBasedViewResolver resolver=new UrlBasedViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        resolver.setCache(false);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
