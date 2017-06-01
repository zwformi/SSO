package com.zw.filter;

import com.zw.cache.JVMCache;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SSOServerFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String urlString=request.getRequestURI();
        //System.out.println(urlString);
            String service = request.getParameter("service");
            String ticket = request.getParameter("ticket");
            Cookie[] cookies = request.getCookies();
            String username = "";
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if ("sso".equals(cookie.getName())) {
                        username = cookie.getValue();
                        break;
                    }
                }
            }
            //获取ticket对应名字时跳过过滤器
            if (null == service && null != ticket) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            System.out.println(username);
            if (null != username && !"".equals(username)) {
                long time = System.currentTimeMillis();
                String timeString = username + time;
                JVMCache.TICKET_AND_NAME.put(timeString, username);
                if(service!=null) {
                    StringBuilder url = new StringBuilder();
                    url.append(service);
                    if (0 <= service.indexOf("?")) {
                        url.append("&");
                    } else {
                        url.append("?");
                    }
                    url.append("ticket=").append(timeString);
/*                    ①相对路径：response.sendRedirect("Manager/index.jsp")
                    生成的地址：原来请求地址+参数生成完整的URL即：
                    http://localhost:8080/项目名/Manager/index.jsp
                       ②绝对路径：response.sendRedirect("/Manager/index.jsp")
                    生成的地址：web服务器本身地址+参数生成完整的URL  即：
                    http://localhost:8080/Manager/index.jsp
                       ③其他web应用地址
                    response.sendRedirect("http://www.baidu.com")
                    容器直接定向到该URL。*/
                    response.sendRedirect(url.toString());
                    return;
                }
                else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }


    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}