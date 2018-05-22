package ru.novand.OrientExpress.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import java.util.List;
import java.util.Locale;

/**
 * mvc-config.xml analogue
 * this class needed that provides configuration and it mapping
 *
 */
@EnableWebMvc  //for request mapping etc.
@Configuration
//@ComponentScan(basePackages = {"ru.novand.OrientExpress.domain.entities"}) //<context:component-scan base-package=''>
//@ComponentScan({"ru.novand.OrientExpress.domain.entities","ru.novand.OrientExpress.domain.DAO","ru.novand.OrientExpress.controllers","ru.novand.OrientExpress.services"})
@ComponentScan({"ru.novand.OrientExpress.domain.pdf","ru.novand.OrientExpress.domain.DAO","ru.novand.OrientExpress.controllers","ru.novand.OrientExpress.services"})
@EnableTransactionManagement  // inclide TransactionManager for management transaction database ;
public class MVCConfig extends WebMvcConfigurerAdapter {

    /**
     * <mvc:resources mapping="/resources/**" location="/resources/" />
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(2);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public ResourceBundleViewResolver resViewResolver() {
        ResourceBundleViewResolver rsviewResolver = new ResourceBundleViewResolver();
        rsviewResolver.setOrder(1);
        rsviewResolver.setBasename("views");
        return rsviewResolver;
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
        //registry.addViewController("/schedule_data.html").setViewName("/schedule/schedule_data");
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/index.html").setViewName("/index");
        registry.addViewController("/login.html").setViewName("/form/login");
        registry.addViewController("/stattionschedule.html").setViewName("/stattionschedule");
        registry.addViewController("/addstation_emp.html").setViewName("/addstation_emp");
        registry.addViewController("/passengerList.html").setViewName("/passengerList");
        registry.addViewController("/trains.html").setViewName("/trains");
        registry.addViewController("/pdf.html").setViewName("/pdf");
        registry.addViewController("/userlk.html").setViewName("/userlk");
        registry.addViewController("/trainRouteList.html").setViewName("/trainRouteList");
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

    /* i18n & L10n */
    @Bean(name = "localeResolver")
    public CookieLocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("en"));
        cookieLocaleResolver.setCookieMaxAge(100000);
        return cookieLocaleResolver;
    }

    /* define locales for site's translate */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/locales/messages");
        resource.setCacheSeconds(1);
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
