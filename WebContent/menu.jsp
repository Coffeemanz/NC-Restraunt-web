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

    <title><%= RB.getString("menu") %></title>

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
          <a class="navbar-brand" href="Controller?command=LogOut"><%= RB.getString("log_out") %></a>
        </div>
      </div>
    </nav>
  
<br>
    <form action="/Restraunt-web/menu.jsp">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            </select>
        </form>	
   
 <br>  
<p align="center"><%= RB.getString("today_have_menu") %>:</p>
<!--<c:out value="${menu}"/>-->


<form name="goToOrder" method="post" action="Controller?command=GoToOrder" align="center" style="margin: 0 auto;">

<div>


<c:forEach var="food" items="${menu}" varStatus="loop">
<input type="checkbox" id="food" name="menu" value="${food.getId() }" > 

<c:out value="${loop.index + 1}" />
<c:out value=") "/>
<c:out value="${food.getFood_name()}"/>
<c:out value=" - "/>
<c:out value="${food.getFood_price()}"/>
<c:out value="$"/><br>

</c:forEach>

<!--  <input type="submit" value="See order"/>     "Controller?command=GoToOrderFromMenuBackCommand"  -->

<c:if test="${empty order}">
    <input type="submit" value=<%= RB.getString("see_order") %>/>
</c:if>
<c:if test="${not empty order}">
    <input type="submit" formaction="Controller?command=GoToOrderFromMenuBack" value=<%= RB.getString("go_back_to_order") %>/>
</c:if>

</form>

<p>
<br>
<%= RB.getString("now_you_have") %>:
<c:out value="${client.getClient_cash()}"/>
$
</p>


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
