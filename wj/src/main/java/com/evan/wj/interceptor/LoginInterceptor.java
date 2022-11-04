package com.evan.wj.interceptor;

import com.evan.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 在SpringBoot中可以直接继承拦截的接口，然后实现接口的preHandle方法，该方法内置代码会在访问需要拦截的页面时自动拦截请求并跳转到登录页面中
// 使用了路径列表(requireAuthPages)，里面写下需要拦截的url，对这些url判断session中是否存在user属性，若存在则放行，否则跳转到login页面

public class LoginInterceptor  implements HandlerInterceptor{

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 需要拦截的uri
        String[] requireAuthPages = new String[]{
                "index",
        };

        // 获取用户请求的session、uri
        HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String uri = httpServletRequest.getRequestURI();
        uri = StringUtils.remove(uri, contextPath+"/");

        // 若用户请求的uri以指定的需要拦截的uri开头，则检查session中的user是否为空，若为空表示用户未登录，将响应重定位到login
        if(begingWith(uri, requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if(user==null) {
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

