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

    <label for="AC_number">Account Number:</label>
    <input type="text" name="AC_number" id="AC_number" required><br><br>

    <input type="submit" value="Submit">

</form>
<div id="success-message" style="display: none;">
    <p>bank accound details added successfully!</p>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("signup-form").addEventListener("submit", function (event) {
            event.preventDefault();

            var formData = {
                bankName: document.getElementById("bankName").value,
                branchName: document.getElementById("branchName").value,
                accountType: document.getElementById("accountType").value,
                AC_number: document.getElementById("AC_number").value
            };

            fetch("/banking-backend/add-back-ac-detail", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
                .then(Response=> {
                    if(Response.status == 201){
                        const successMessage = document.getElementById("success-message");
                        successMessage.style.display = "block";
                    }
                    return response.json();
                })
                .then(data => {
                // Handle the response as needed
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
