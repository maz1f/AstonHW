<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Store page</title>
</head>
<body>
<form action="/store/view" method="get">
    <input type="submit" value="Main view page">
</form>
    <h4>Method type: ${stores.method}</h4>
    <c:forEach items="${stores.body}" var="store">
        Store name: ${store.name} <br>
        Address: ${store.address} <br>
        <form action="/store/view/${store.id}" method="get">
            <input type="submit" value="Get this store info">
        </form>
        <hr>
    </c:forEach>


    <h5>Add new store:</h5>
    <form:form method="post" action="/store/view" modelAttribute="form_store">
        <table>
            <tr>
                <td><label for="post_name">Store name</label></td>
                <td><input name="name" id="post_name"/></td>
            </tr>
            <tr>
                <td><label for="post_address">Address</label></td>
                <td><input name="address" id="post_address"/></td>
                <td></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
