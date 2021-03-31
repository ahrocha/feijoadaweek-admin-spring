package br.com.feijoadaweek.admin.service;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.feijoadaweek.admin.filter.TransactionFilter;

@Configuration
public class AppConfigService {
    @Bean
    public FilterRegistrationBean<TransactionFilter> filter() {
        FilterRegistrationBean<TransactionFilter> bean = new FilterRegistrationBean<>();
 
        bean.setFilter(new TransactionFilter());
        bean.addUrlPatterns("/restaurantes");
        bean.addUrlPatterns("/restaurante/*");
 
        return bean;
    }
}
