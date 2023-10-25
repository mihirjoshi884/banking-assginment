<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form id="login-form">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Log In">
</form>
<div id="message" style="display: none;"></div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("login-form").addEventListener("submit", function (event){
            event.preventDefault();
            var formData = {
                username: document.getElementById("username").value,
                password: document.getElementById("password").value
            };
            fetch("/banking-backend/login", {
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
