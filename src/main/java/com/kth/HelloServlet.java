package com.kth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public HelloServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello Servlet??</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello Servlet!!</h1>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
