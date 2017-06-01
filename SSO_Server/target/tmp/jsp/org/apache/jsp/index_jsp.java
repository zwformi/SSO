package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">\r\n");
      out.write("    <title>sso登陆系统</title>\r\n");
      out.write("    <script typet=\"text/javascript\" src=\"http://libs.baidu.com/jquery/1.9.1/jquery.min.js\"></script>\r\n");
      out.write("    <style>\r\n");
      out.write("        .input_line{\r\n");
      out.write("            width: 30%;\r\n");
      out.write("            background:#ccc;\r\n");
      out.write("        }\r\n");
      out.write("        label{\r\n");
      out.write("            display: inline-block;\r\n");
      out.write("            width: 64px;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h2>ZW_Login</h2>\r\n");
      out.write("<form>\r\n");
      out.write("    <div class=\"input_line\">\r\n");
      out.write("    <label for=\"username\">用户名：</label><input type=\"text\" name=\"username\" id=\"username\" placeholder=\"用户名\" size=\"40\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"input_line\">\r\n");
      out.write("    <label for=\"password\">密码：</label><input type=\"password\" name=\"password\" id=\"password\" placeholder=\"密码\" size=\"40\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"input_line\">\r\n");
      out.write("    <label for=\"service\">客户端：</label><input type=\"text\" name=\"service\" id=\"service\" placeholder=\"客户端\" size=\"40\" disabled=\"disabled\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"input_line\">\r\n");
      out.write("    <input type=\"button\" value=\"登陆\" onclick=\"login()\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("    window.onload=function () {\r\n");
      out.write("        var service = getUrlParam(\"service\");\r\n");
      out.write("        $(\"#service\").val(decodeURIComponent(service));\r\n");
      out.write("    };\r\n");
      out.write("    function getUrlParam(name) {\r\n");
      out.write("        var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\"); //构造一个含有目标参数的正则表达式对象\r\n");
      out.write("        var r = window.location.search.substr(1).match(reg);  //匹配目标参数\r\n");
      out.write("        if (r != null) return decodeURI(r[2]); return null; //返回参数值\r\n");
      out.write("    }\r\n");
      out.write("    function  login() {\r\n");
      out.write("        var dat={};\r\n");
      out.write("        dat.username=$(\"#username\").val();\r\n");
      out.write("        dat.password=$(\"#password\").val();\r\n");
      out.write("        if($(\"#service\").val()!=\"null\")\r\n");
      out.write("        dat.service=$(\"#service\").val();\r\n");
      out.write("/*        $.post(\"/SSO_Server/user/login\",dat, function (data, textStatus) {\r\n");
      out.write("          alert(data);\r\n");
      out.write("        })*/\r\n");
      out.write("        $.ajax({\r\n");
      out.write("            async : false,\r\n");
      out.write("            url : 'http://localhost:8081/SSO_Server/user/login',\r\n");
      out.write("            type : \"GET\",\r\n");
      out.write("            dataType : 'jsonp',\r\n");
      out.write("            jsonp : 'Jcallback',\r\n");
      out.write("            data : dat,\r\n");
      out.write("            timeout : 5000,\r\n");
      out.write("            success : function(json) {\r\n");
      out.write("                    alert(json.resString);\r\n");
      out.write("                    if(json.resCode==0&&json.newUrl!=\"\"){\r\n");
      out.write("                        window.location.href=json.newUrl;\r\n");
      out.write("                    }\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
