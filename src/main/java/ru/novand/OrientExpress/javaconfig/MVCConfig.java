package ru.novand.OrientExpress.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * mvc-config.xml analogue
 * this class needed that provides configuration and it mapping
 *
 */
@EnableWebMvc  //for request mapping etc.
@Configuration
//@ComponentScan(basePackages = {"ru.novand.OrientExpress.domain.entities"}) //<context:component-scan base-package=''>
//@ComponentScan({"ru.novand.OrientExpress.domain.entities","ru.novand.OrientExpress.domain.DAO","ru.novand.OrientExpress.controllers","ru.novand.OrientExpress.services"})
@ComponentScan({"ru.novand.OrientExpress.domain.DAO","ru.novand.OrientExpress.controllers","ru.novand.OrientExpress.services"})
@EnableTransactionManagement  //включает TransactionManager для управления транзакциями БД;
public class MVCConfig extends WebMvcConfigurerAdapter {

    /**
     * <mvc:resources mapping="/resources/**" location="/resources/" />
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public RequestContextListener getRequestContextListener() {
        return new RequestContextListener();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/schedule.html").setViewName("/schedule/schedule");
        registry.addViewController("/paygate.html").setViewName("/paygate");
        //registry.addViewController("/request-data.html").setViewName("/schedule/request-data");
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/index.html").setViewName("/index");
        registry.addViewController("/login.html").setViewName("/form/login");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getJacksonHttpMessageConverter());
    }

    @Bean(name = "jacksonHttpMessageConverter")
    public MappingJackson2HttpMessageConverter getJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setPrettyPrint(true);
        return converter;
    }

}
