
<html>
<%@ page import="java.io.*, java.util.*" %>
<body>
<% String username = request.getParameter("username");
String password = request.getParameter("password");
%>
<%out.println("Username: " + username);
out.println("Password: " + password);
%>
<%
    String buttonLabel1 = " Add to Shopping Cart";
%>
<button type="button"><%= buttonLabel1 %></button>
<%
    String buttonLabel2 = "View Shopping Cart";

%>
<button type="button"><%= buttonLabel2 %></button>
</body>
</html>