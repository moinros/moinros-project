package com.moinros.config.web;

import com.moinros.config.web.filter.EncoidingFilter;
import com.moinros.config.web.interceptor.SystemInterceptor;
import com.moinros.config.web.interceptor.UserInterceptor;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
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

    /**
     * 注释: 配置 http请求自动跳转到https
     */
    @Bean
    public org.apache.catalina.connector.Connector connector() {
        Connector connector = new org.apache.catalina.connector.Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(443);
        return connector;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(org.apache.catalina.connector.Connector connector) {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
