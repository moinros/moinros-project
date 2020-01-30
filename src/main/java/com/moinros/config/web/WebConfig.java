package com.moinros.config.web;

import com.moinros.config.web.filter.EncoidingFilter;
import com.moinros.config.web.interceptor.SystemInterceptor;
import com.moinros.config.web.interceptor.UserInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置不拦截的路径
        String[] excludePath = {"/wp-content/**", "**.ico", "**.js", "**.css"};
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**").excludePathPatterns();
        registry.addInterceptor(new SystemInterceptor()).addPathPatterns("/system/**").excludePathPatterns(excludePath);

    }

    /**
     * 注释: 将自定义过滤器添加到容器中
     *
     * @return FilterRegistrationBean<EncoidingFilter>
     */
    @Bean
    public FilterRegistrationBean<EncoidingFilter> filterRegist() {
        FilterRegistrationBean<EncoidingFilter> frBean = new FilterRegistrationBean<EncoidingFilter>();
        frBean.setFilter(new EncoidingFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
