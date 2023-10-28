<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions-details</title>

</head>
<body>
<header>
    <h1>Transactions</h1>
</header>


<main>
    <div id="transaction-container">
        <div id="user-bank-account-container">
            <p>user's name is : ${sessionScope["user-details"].nanme}</p>
            <p>user's id is : ${sessionScope["user-details"].userId}</p>
            <p>user's account number is : ${sessionScope["bank-details"].accountNumber}</p>
            <p>user's bank name is : ${sessionScope["bank-details"].bankName}</p>
            <p>transaction id is : ${sessionScope["allAccountStatements"].statementId}</p>

        </div>
        <div id="transaction-table-container">
            <table id="transaction-table">
                <tr>
                    <th>transaction id</th>
                    <th>date of transaction</th>
                    <th>description</th>
                    <th>credit amount</th>
                    <th>debit amount</th>
                    <th>balance</th>


                </tr>
                <c:forEach items="${transactions}" var="transaction">
                    <tr>
                        <td>${transaction.transactionId}</td>
                        <td>${transaction.dateOfTransaction}</td>
                        <td>${transaction.description}</td>
                        <td>${transaction.credit}</td>
                        <td>${transaction.debit}</td>
                        <td>${transaction.balance}}</td>

                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</main>


</body>
</html>
