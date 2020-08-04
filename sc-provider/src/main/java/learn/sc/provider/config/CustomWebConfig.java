package learn.sc.provider.config;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc //不要加这个,会造成WebMvcAutoConfiguration失效，如果我们不需要自动装配，完全自己控制可以使用
public class CustomWebConfig implements WebMvcConfigurer {
}
