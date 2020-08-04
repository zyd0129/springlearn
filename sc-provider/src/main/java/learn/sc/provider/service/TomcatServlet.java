package learn.sc.provider.service;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {}) // 这个有点DI的思想
@EnableWebMvc
public class TomcatServlet implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        // 如何优雅的集成其它
    }

    public void tomcat() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("/", "/");
        tomcat.start();
        tomcat.getServer().await();
    }
}
