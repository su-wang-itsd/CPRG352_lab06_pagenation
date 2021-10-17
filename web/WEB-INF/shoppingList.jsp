<%-- 
    Document   : shoppingList
    Created on : Oct 14, 2021, 3:17:34 PM
    Author     : 845593
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>${username}</p>
        <p><a href="ShoppingList?action=logout">Logout</a></p>
        <form action="" method="post">
            <h2>Add Item</h2>
            <input type="text" name="item">
            <input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">


        </form>
        <form action="" method="post">
             <c:if test="${size>=0}">
            <ul>
                <c:forEach var="itemname" items="${pageItems}">
                    <li><input type="radio" name="item" value="${itemname.toString()}">${itemname.toString()}</li>
                </c:forEach>

            </ul>
             </c:if>
            <input type="submit" value="delete">
            <input type="hidden" name="action" value="delete">
        </form>
        <c:if test="${page >1}">
        <a href="ShoppingList?page=${page-1}">&lt; Back</a></c:if>
          <c:if test="${page < items.size()/10}">
        <a href="ShoppingList?page=${page+1}">Next &gt;</a></c:if>
       
    </body>
</html>
