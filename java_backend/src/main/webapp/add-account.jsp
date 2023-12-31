<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Bank Account Details</title>
    <!-- Add any CSS or styling links here if needed -->
</head>
<body>
<h1>Add Bank Account Details</h1>

<form id="add-bank-account-form">
    <label for="bankName">Bank Name:</label>
    <input type="text" name="bankName" id="bankName" required><br><br>

    <label for="branchName">Branch Name:</label>
    <input type="text" name="branchName" id="branchName" required><br><br>

    <label for="accountType">Account Type:</label>
    <input type="text" name="accountType" id="accountType" required><br><br>

    <label for="accountNumber">Account Number:</label>
    <input type="text" name="accountNumber" id="accountNumber" required><br><br>

    <input type="submit" value="Submit">

</form>
<div id="success-message" style="display: none;">
    <p>bank account details added successfully!</p>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("add-bank-account-form").addEventListener("submit", function (event) {
            event.preventDefault();

            var formData = {
                bankName: document.getElementById("bankName").value,
                branchName: document.getElementById("branchName").value,
                accountType: document.getElementById("accountType").value,
                accountNumber: document.getElementById("accountNumber").value
            };

            fetch("/banking-backend/add-back-ac-detail", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
                .then(response => {  // Change 'Response' to 'response' here
                    if (response.status === 201) {
                        const successMessage = document.getElementById("success-message");
                        successMessage.style.display = "block";
                    }
                    return response.json();
                })
                .then(data => {
                    // Handle the response data as needed
                    console.log(data);
                })
                .catch(error => {
                    // Handle any errors
                    console.error(error);
                });

        });
    });
</script>
</body>
</html>
