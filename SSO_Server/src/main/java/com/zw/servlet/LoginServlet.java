package com.zw.servlet;

import com.alibaba.fastjson.JSON;
import com.zw.cache.JVMCache;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -3170191388656385924L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> rs = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String service = request.getParameter("service");

        if ("cloud".equals(username) && "cloud".equals(password)) {
            Cookie cookie = new Cookie("sso", username);
            cookie.setPath("/");
            response.addCookie(cookie);

            long time = System.currentTimeMillis();
            String timeString = username + time;
            JVMCache.TICKET_AND_NAME.put(timeString, username);
            //System.out.println(service);
            if (null != service&&!"".equals(service)){
                StringBuilder url = new StringBuilder();
                url.append(service);
                if (0 <= service.indexOf("?")) {
                    url.append("&");
                } else {
                    url.append("?");
                }
                url.append("ticket=").append(timeString);
                System.out.println(url.toString());
                rs.put("resCode",0);
                rs.put("resString","登陆成功！");
                rs.put("newUrl",url.toString());
               // response.sendRedirect(url.toString());
               // return;
            } else {
                rs.put("resCode",0);
                rs.put("resString","登陆成功！");
                rs.put("newUrl","/SSO_Server/jsp/welcome.jsp");
                /*response.sendRedirect("/SSO_Server/index.jsp");*/
            }
        } else {
            rs.put("resCode",1);
            rs.put("resString","密码或用户名错误！");
           /* response.sendRedirect("/SSO_Server/index.jsp?service=" + service);*/
        }
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String resJSON = JSON.toJSONString(rs);
        String jsonpCallback = request.getParameter("Jcallback");//客户端请求参数
        out.println(jsonpCallback+"("+resJSON+")");//返回jsonp格式数据
        out.flush();
        out.close();
    }

}
