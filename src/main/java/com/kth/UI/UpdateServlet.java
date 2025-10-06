package com.kth.UI;

import com.kth.BO.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String query = request.getParameter("title");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            user.updateShoppingCart(query);
            user.getShoppingCart();
            RequestDispatcher dispatcher = request.getRequestDispatcher("items.jsp");
            dispatcher.forward(request, response);
        }
    }
