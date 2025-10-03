<%@ page import="java.util.*, com.kth.BO.Item" %>
<html>
<body>
<h2>Games found:</h2>

<%
    Collection<Item> results = (Collection<Item>) request.getAttribute("searchResults");
    if (results == null || results.isEmpty()) {
%>
    <p>No results found.</p>
<%
    } else {
%>
    <ul>
        <% for (Item item : results) { %>
            <li>
                <%= item.getTitle() %> - <%= item.getGenre() %> - $<%= item.getPrice() %>
                <form action="addToCart" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= item.getId() %>" />
                    <button type="submit">Add to Cart</button>
                </form>
            </li>
        <% } %>
    </ul>
<%
    }
%>

<a href="search.jsp">Back to Search</a>
</body>
</html>
