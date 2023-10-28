<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style_sheet/nav-bar.css' />">
</head>
<body>
<script>

</script>
<header>
    <nav>
        <ul>
            <li><a href="/add-account.jsp">Add Bank Account Details</a></li>
            <li><a href="/get-statement.jsp">Get Consolidated Statement</a></li>
            <li><a href="/bank-details.jsp"> bank statement</a></li>
            <li><a href="get-new-bank-statement.jsp"> new bank statement</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
    <h1>Welcome ${sessionScope['user-details'].name} </h1>
</header>

<p>hello, this is your dash board, you will find your entire information here</p>
<container>
    <div>
        <p>your user-id is: </p>
        <p>${sessionScope['user-details'].userId}</p>
    </div>

    <div>
        <p>your name: </p>
        <p>${sessionScope['user-details'].name}</p>
    </div>

    <div>
        <p>your phone number is: </p>
        <p>${sessionScope['user-details'].phone}</p>
    </div>

    <div>
        <p>your email address is : </p>
        <p>${sessionScope['user-details'].email}</p>
    </div>

    <div>
        <p>your username is : </p>
        <p>${sessionScope['user-details'].userName}</p>
    </div>

    <div>
        <p>your address is : </p>
        <p>${sessionScope['user-details'].address}</p>
    </div>

</container>


<!-- You can add more content or features here as needed -->

<a href="/login.jsp">Logout</a>
</body>
</html>
