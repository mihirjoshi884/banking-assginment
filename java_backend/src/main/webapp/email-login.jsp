<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Email Login</title>
</head>
<body>
<h1>Email Login</h1>
<form id="email-login-form">
    <label for="email">email:</label>
    <input type="text" id="email" name="email" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Log In">
</form>
<div id="message" style="display: none;"></div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("email-login-form").addEventListener("submit", function (event){
            event.preventDefault();
            var formData = {
                email: document.getElementById("email").value,
                password: document.getElementById("password").value
            };
            fetch("/banking-backend/email-login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (response.status === 201) {
                        window.location.href = "dashboard.jsp"; // Redirect on successful login
                    }
                    else if (response.status === 401) {
                        // Display a retry message
                        var messageElement = document.getElementById("message");
                        messageElement.style.display = "block";
                        messageElement.textContent = "Login failed. Please retry.";
                    }
                })
        });
    });
</script>
</body>
</html>
