/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.20
 * Generated at: 2017-12-23 09:22:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype>\n");
      out.write("<html >\n");
      out.write("<head>\n");
      out.write("\n");
      out.write(" <link rel=\"stylesheet\"\n");
      out.write("   href=\"bower_components/angular-material/angular-material.css\" >\n");
      out.write("</head>\n");
      out.write("<body ng-app=\"todoApp\">\n");
      out.write("<div ui-view></div>\n");
      out.write("\t<!-- <div layout=\"column\" ui-view></div> -->\n");
      out.write("\t<!--  <div layout flex ui-view></div> -->\n");
      out.write("</body>\n");
      out.write("<!-- -================================================================================================= -->\n");
      out.write("<script src=\"bower_components/angular/angular.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-animate/angular-animate.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-aria/angular-aria.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-material/angular-material.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-ui-router/release/angular-ui-router.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-messages/angular-messages.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-sanitize/angular-sanitize.js\"></script>\n");
      out.write("<script src=\"bower_components/colorpicker-master/dist/colorPicker.js\"></script>\n");
      out.write("<script src=\"https://connect.facebook.net/enUS/all.js\"></script>\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("<!-- -================================================================================================= -->\n");
      out.write("<script type=\"text/javascript\" src=\"scripts/app.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"controller/registrationController.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"controller/loginController.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"controller/homeController.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"controller/setPasswordController.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"controller/passwordController.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"controller/dummyController.js\"></script>\n");
      out.write("\n");
      out.write("<!-- -================================================================================================= -->\n");
      out.write("<script type=\"text/javascript\" src=\"services/registrationService.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"services/loginService.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"services/homeService.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"services/passwordService.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"services/setPasswordService.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"services/dummyService.js\"></script>\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\"  href=\"styles/topNavBar.css\">\n");
      out.write("<link rel=\"stylesheet\"  href=\"styles/home.css\">\n");
      out.write("<link rel=\"stylesheet\"  href=\"styles/archive.css\">\n");
      out.write("<link rel=\"stylesheet\"  href=\"bower_components/colorpicker-master/dist/colorPickerStyle.css\">\n");
      out.write("\n");
      out.write("<!-- -================================================================================================= -->\n");
      out.write("<script type=\"text/javascript\" src=\"directives/homeDirectives.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"directives/noteDirctv.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-base64/angular-base64.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-base64-upload/dist/angular-base64-upload.js\"></script>\n");
      out.write("<script src=\"bower_components/angular-base64-upload/dist/angular-base64-upload.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- -================================================================================================= -->\n");
      out.write("\t <script src=\"bower_components/angular-material-datetimepicker/dist/angular-material-datetimepicker.min.js\"></script>\n");
      out.write(" \t<script src=\"bower_components/angular-material-datetimepicker/dist/angular-material-datetimepicker.min.js.map\"></script>\n");
      out.write(" \t <link rel=\"stylesheet\" href=\"bower_components/angular-material-datetimepicker/dist/material-datetimepicker.min.css\">\n");
      out.write("\t<script src=\"bower_components/moment/min/moment.min.js\"></script>\n");
      out.write("\t\t\n");
      out.write("\t<link rel=\"stylesheet\" href=\"bower_components/angular-toastr/dist/angular-toastr.css\">\n");
      out.write("\t<script src=\"bower_components/angular-toastr/dist/angular-toastr.tpls.js\"></script>\n");
      out.write("<!--==================================================================================================== -->\t\n");
      out.write("\t<script src=\"bower_components/ng-file-upload/ng-file-upload-shim.min.js\"></script>\n");
      out.write("\t<script src=\"bower_components/ng-file-upload/ng-file-upload.min.js\"></script>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
