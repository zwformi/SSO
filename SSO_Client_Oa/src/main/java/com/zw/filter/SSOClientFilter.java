package com.zw.filter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class SSOClientFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String ticket = request.getParameter("ticket");
        String url = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");

        if (null == username) {
            if (null != ticket && !"".equals(ticket)) {
                PostMethod postMethod = new PostMethod("http://localhost:8081/SSO_Server/ticket");
                postMethod.addParameter("ticket", ticket);
                HttpClient httpClient = new HttpClient();
                try {
                    httpClient.executeMethod(postMethod);
                    username = postMethod.getResponseBodyAsString();
                    postMethod.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != username && !"".equals(username)) {
                    session.setAttribute("username", username);
                    filterChain.doFilter(request, response);
                } else {
                    response.sendRedirect("http://localhost:8081/SSO_Server/index.jsp?service=" + url);
                }
            } else {
                response.sendRedirect("http://localhost:8081/SSO_Server/index.jsp?service=" + url);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
