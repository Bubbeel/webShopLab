<html>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    String admin = "admin";
    String adminPass = "1234";

    if (username != null && password != null
        && username.equals(admin) && password.equals(adminPass)) {

        response.sendRedirect("items.jsp");
        return; // stop here so nothing else runs
    } else {
        out.println("<p style='color:red'>Invalid username or password.</p>");
        out.println("<a href='index.jsp'>Try again.</a>");
    }
%>
</body>
</html>