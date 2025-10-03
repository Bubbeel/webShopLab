package com.kth.UI;

import com.kth.BO.Item;
import com.kth.BO.User;
import com.kth.DB.ItemDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = User.authenticate(username, password);

        if (user != null) {
            //need to fetch game title here somehow
            user.updateShoppingCart("Baldurs Gate 3");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("items.jsp");
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

