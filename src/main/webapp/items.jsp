
<html>

<%@ page import="java.io.*, java.util.*" %>
<body>
<%out.println("Username: " + request.getParameter("username"));
out.println("Password: " + request.getParameter("password"));
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