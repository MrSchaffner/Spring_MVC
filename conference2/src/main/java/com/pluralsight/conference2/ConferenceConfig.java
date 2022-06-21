package com.pluralsight.conference2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

//Manually imported from thymeleaf.org
//import org.thymeleaf.spring5.templateresolver;
//import org.springframework.beans.factory.Aware;
//import org.springframework.context.ApplicationContextAware;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;


import java.util.Locale;

@Configuration
public class ConferenceConfig implements WebMvcConfigurer {

    //used with webmvcConfigurer to help display pdf
    //when /files is put in address bar, redirects to /WEB-INF/pdf/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/files/**")
        .addResourceLocations("/WEB-INF/pdf/");
    }

    @Override // overrides WebMvcConfigurer
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean //for I18N
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean //
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }



    @Bean //was previously done behind scenes with spring boot annotation in conference2application class
    public ViewResolver viewResolver(){
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
                bean.setPrefix("/WEB-INF/jsp/");
    bean.setSuffix(".jsp");
    bean.setOrder(0);
    return bean;

    }


    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

}
