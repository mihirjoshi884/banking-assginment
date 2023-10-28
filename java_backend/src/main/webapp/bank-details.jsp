<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your JSP Page</title>

</head>
<body>
<header>
    <h1>bank details</h1>
</header>

<nav>
    <ul>
        <li><a href="/add-account">Add Bank Account Details</a></li>
        <li><a href="/get-statement">Get Consolidated Statement</a></li>
        <li><a href="/logout">Logout</a></li>
    </ul>
</nav>

<main>
    <div id="bank-details-container">
        <div id="user-info-container">
            <p> name of the user: ${sessionScope['user-details'].name}</p>

        </div>
        <div id="bank-detail-info-container">
            <table id="bank-details-table">
                <tr>
                    <th>bank ID</th>
                    <th>bank name</th>
                    <th>account number</th>
                    <th>branch name</th>
                    <th>account type</th>
                    <th>Account Statement</th>

                </tr>
                <c:forEach items="${bankDetails}" var="bankDetails">
                    <tr>
                        <td>${bankDetails.bankId}</td>
                        <td>${bankDetails.bankName}</td>
                        <td>${bankDetails.accountNumber}</td>
                        <td>${bankDetails.branchName}</td>
                        <td>${bankDetails.accountType}</td>
                        <td><a href="account-statement.jsp"></a></td>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>


</main>

<script>

</script>
</body>
</html>
