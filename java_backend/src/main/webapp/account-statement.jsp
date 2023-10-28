<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account-statement</title>
    <!-- Add any CSS or styling links here if needed -->
</head>
<body>
<header>
    <h1>accountStatement</h1>
</header>


<main>
    <div id="account-statement-container">
        <div id="user-bank-details-container">
            <p> name of the user: ${sessionScope["user-details"].name}</p>
            <p>account number of the user: ${sessionScope["bank-details"].accountNumber}</p>
            <p>bank name: ${sessionScope["bank-details"].bankName}</p>
            <p> bank id: ${sessionScope["bank-details"].bankId}</p>

        </div>
        <div id="account-statement-table-container">
            <table id="bank-details-table">
                <tr>
                    <th>statement ID</th>
                    <th>statement date</th>
                    <th>periodDate</th>
                    <th>number of Transaction</th>
                    <th>total credit amount</th>
                    <th>total debit amount</th>
                    <th>opening balance</th>
                    <th>closing balance</th>
                    <th>transactions</th>

                </tr>
                <c:forEach items="${accountStatements}" var="AccountStatement">
                    <tr>
                        <td>${AccountStatement.statementId}</td>
                        <td>${AccountStatement.statementDate}</td>
                        <td>${AccountStatement.periodDate}</td>
                        <td>${AccountStatement.numberOfTransactions}</td>
                        <td>${AccountStatement.totalCreditAmount}</td>
                        <td>${AccountStatement.totalDebitAmount}</td>
                        <td>${AccountStatement.openingBanlance}</td>
                        <td>${AccountStatement.closingBalance}</td>
                        <td><a href="transactins.jsp"></a></td>

                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</main>


</body>
</html>
