package spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Mordr on 02.03.2017.
 */
public class WebAppInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        WebApplicationContext webContext = context;
        servletContext.addListener(new ContextLoaderListener(webContext));
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamic=servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
        dynamic.setAsyncSupported(true);
    }
}
