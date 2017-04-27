<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,
                 java.net.*,
                 java.util.*" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<% 
	Locale locale = null;
	String lang = request.getParameter("language");

	if (lang != null) {
	  locale = new Locale(lang);
	} else {
	  locale = request.getLocale();
	}
	ResourceBundle RB = ResourceBundle.getBundle("app", locale);
%>  


	
	<!DOCTYPE html>

<html lang="${language}">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title><%= RB.getString("greeting") %></title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">-->

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
<!--    <%!
   // ===========================================
   //    Helper method to get the language specific greeting
   // ===========================================

   /**
   * Gets the greeting message appropriate for
   * this locale
   *
   private String getGreeting
      (HttpServletRequest request)
   {
      Locale locale = null;
      String lang = request.getParameter("language");
      
      if (lang != null) {
        locale = new Locale(lang);
      } else {
        locale = request.getLocale();
      }
      ResourceBundle RB = ResourceBundle.getBundle("app", locale);
      String greeting = RB.getString("greeting");
      return greeting;
   }*/

%> 
-->
  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="Index.jsp"><%= RB.getString("delivery_system") %></a>
        </div>
      </div>
    </nav>
  <br>
  <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            </select>
        </form>
        <br>
	<div class="container">
    <div class="starter-template"><h1 align="center"><%= RB.getString("greeting") %></h1>
	<br>
  <a class="btn btn-lg btn-primary" href="login.jsp" role="button"><%= RB.getString("login") %></a><br>
  <br>
  <a class="btn btn-lg btn-primary" href="login_admin.jsp" role="button"><%= RB.getString("login_admin") %></a><br>
  <br>
  <a class="btn btn-lg btn-primary" href="registration.jsp" role="button"><%= RB.getString("register") %></a><br>
    </div>
  
    </div>
   


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
