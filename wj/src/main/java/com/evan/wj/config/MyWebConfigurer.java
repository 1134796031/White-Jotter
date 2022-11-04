package com.evan.wj.config;

import com.evan.wj.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

// 这个类可以理解为所有拦截器Interceptor的入口，其包含两个函数
// 函数1返回一个LoginInterceptor类的实例对象
// 函数2为拦截器注册表registry同归.add方法添加拦截器，并配置该拦截器作用的url路径
//
// 即用户访问一个url首先通过Configurer中registry.addPath方法判断url是否需要拦截
// 若需要拦截则将该url导向registry.addInterceptor中指定的拦截器
//
// 注意，通过registry.excludePath指定/index.html不会触发拦截器，而/index会触发拦截器
// 这是因为任何uri经过Configurer都会触发拦截器，并被重定向到/login，登陆后/login再经过Configurer触发拦截器
// 由于已经登录，拦截器会放行，再根据单页面应用部署于后端而配置的./error/ErrorConfig，这个./login请求最终被重定向到/index.html
// 即对于单页面应用，登陆后经拦截器放行后，最终会落在/index.html页面上，因此需要将这个地址放在拦截器触发的排除项中

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 对除了"/index.html"之外的所有路径应用拦截器
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:/IntelliJ IDEA Project/White-Jotter-my/upImg");
    }

}

