<%@ page import="com.kth.BO.User, com.kth.BO.Item, java.util.ArrayList, com.kth.BO.ItemHandler, com.kth.UI.ItemInfo" %>
<html>
<body>
<%
    User user = (User) session.getAttribute("user");
    ArrayList<ItemInfo> cart = user != null ? ItemHandler.getUserShoppingCart(user) : new ArrayList<>();
%>

<h3>Welcome, <%= user != null ? user.getUsername() : "Guest" %></h3>
<p>Items in cart: <%= cart.size() %></p>

<form action="search" method="get">
    <input type="text" name="gameSearch" placeholder="Search for games!" required />
    <button type="submit">Search</button>
</form>
<h2>Your Shopping Cart:</h2>
<% if (cart.isEmpty()) { %>
    <p>No items in your cart.</p>
<% } else { %>
    <ul>
    <% for (ItemInfo item : cart) { %>
        <li><%= item.getTitle() %> - $<%= item.getPrice() %></li>
    <% } %>
    </ul>
<% } %>

</body>
</html>
