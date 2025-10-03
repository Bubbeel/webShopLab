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

                <!-- ✅ send title -->
                <form action="update" method="post" style="display:inline;">
                    <input type="hidden" name="title" value="<%= item.getTitle() %>" />
                    <button type="submit">Add to Cart</button>
                </form>
            </li>
        <% } %>
    </ul>
<%
    }
%>

<a href="items.jsp">Back to Search</a>
</body>
</html>
5